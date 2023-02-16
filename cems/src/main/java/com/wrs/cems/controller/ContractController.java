package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.VariableLengthInstruction;
import com.wrs.cems.bean.Contract;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IContractService;
import com.wrs.cems.service.IEmployeeService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ContractController {
    @Autowired
    private IContractService contractService;

    @Autowired
    private IEmployeeService employeeService;

    private static final String[] fileType ={".xls",".xlsm",".docx",".doc",".pdf",".zip",".rar",".7z"};


    // 查询所有合同返回列表页面
    @RequestMapping("/contracts")
    public String list(HttpServletRequest request,Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        String url = request.getServletPath();
        model.addAttribute("url",url);


        //查找数据
        List<Contract> contracts = contractService.getContractAndEmp();
        //将查找出的结果封装到PageInfo对象中，这个对象包含了 emps集合和关于分页的方法，如下
        //pageInfo.getPageNum();
        //pageInfo.getPages(); 得到总页数
        //pageInfo.getNextPage(); 得到下一页
        //pageInfo.getPrePage(); 得到前一页
        PageInfo<Contract> pageInfo = new PageInfo<>(contracts);
        //用Model传递对象到page页面
        model.addAttribute("pageInfo", pageInfo);
        return "contract/contract";
    }

    // 来到合同添加页面
    @GetMapping("/contract")
    public String toAddPage(Model model) {
        // 来到添加页面，查出所有的员工，在页面显示
        List<Employee> emps = employeeService.getAllEmps();
        model.addAttribute("emps", emps);
        return "contract/add";
    }

    // 合同添加
    // SpringMVC 自动将请求参数和入参对象的属性进行一一绑定，要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @RequestMapping("/contract")
    public String addContract(Contract contract) {
        // 保存合同
        contractService.addContract(contract);
        // 来到合同列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/contracts";
    }

    // 来到修改页面，查出当前合同，在页面显示
    @GetMapping("/contract/{CId}")
    public String toUpdatePage(@PathVariable("CId") Integer CId, Model model) {
        Contract contract = contractService.getContractById(CId);
        model.addAttribute("contract", contract);

        /*// 页面要显示所有的员工列表
        List<Employee> emps = employeeService.getAllEmps();*/
        String wid = contract.getCWorkId();
        Employee emps = employeeService.getEmpByWorkID(wid);
        model.addAttribute("emps", emps);
        // 回到修改页面（add是一个修改添加二合一的页面）
        return "contract/add";
    }

    // 合同修改，需要提交合同CId
    @PutMapping("/contract")
    public String updateContract(Contract contract) {
        contractService.updateContract(contract);
        return "redirect:/contracts";
    }

    //合同删除
    @DeleteMapping("/contract/{CId}")
    public String deleteContractById(@PathVariable("CId") Integer CId) {
        contractService.deleteContractById(CId);
        return "redirect:/contracts";
    }

    //提交表单+传回前端的映射
    @RequestMapping("/consearch")
    public String queryContract( String name, String CWorkId,
                                Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        model.addAttribute("name",name);
        model.addAttribute("CWorkId",CWorkId);

        String url = "/consearch";
        model.addAttribute("url",url);

        if ((name.equals("")||name.length()==0)&&(CWorkId.equals("")||CWorkId.length()==0)){
            return "redirect:/contracts";
        }
        //查找数据
        List<Contract> contracts = contractService.query(name,CWorkId);
        if (contracts.size()==0){
            String info ="查询不到该信息！";
            model.addAttribute("info",info);
        }

        PageInfo<Contract> pageInfo = new PageInfo<>(contracts);

        model.addAttribute("pageInfo", pageInfo);
        return "contract/contract";
    }

    @RequestMapping("/contract/fileUp/{CId}")
    private String fileUp(@PathVariable("CId") Integer CId, Model model) {
        Contract contract = contractService.getContractById(CId);
        model.addAttribute("contract", contract);
        return "contract/fileUp";
    }

    //单一文件上传
    @RequestMapping("/fileUp")
    //@ResponseBody
    private String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("CId") Integer CId,Model model) {
        if (file.isEmpty()) {
            model.addAttribute("msg", "文件上传失败！");
            System.err.println("上传失败！");
            return "redirect:/contracts";
        }
        String filename = file.getOriginalFilename();
        //String filePath = request.getServletContext().getRealPath("/upload");
        String filePath = "";
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            //filePath = getClass().getClassLoader().getResource("classpath:").toURI().getPath();
            try {
                filePath = URLDecoder.decode(filePath, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //避免文件重复覆盖
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //时间戳分类文件
        //String time = new SimpleDateFormat("YYYY-MM").format(new Date());
        // 从原文件名中得到扩展名
//        String suffix = "";
//        int beginIndex = filename.lastIndexOf(".");
//        if (beginIndex > 0) {
//            suffix = filename.substring(beginIndex);
//            System.out.println("suffix="+suffix);
//        }
//        for (String ft : fileType) {
//            System.out.print(ft);
//            if (!suffix.equals(ft)) {
//                System.err.println("上传文件格式有误！");
//                //return "contract/fileUp";
//            }
//        }

        String newName = uuid + "_" + filename;
        Integer result = contractService.updateFile(CId, newName);
        System.out.println(result);

        String realPath = filePath + newName;
        File dest = new File(realPath);
        //检测是否存在目录，无，则创建
        if (!dest.getParentFile().exists()) {
            //新建文件夹 多级目录
            dest.getParentFile().mkdirs();
        }
        try {
            //文件写入
            file.transferTo(dest);
            System.out.println(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/contracts";
        //return "success";
    }


    @RequestMapping("/download")
    public String download(@RequestParam("filename") String filename,
                           HttpServletResponse response) {
        try {
            // 文件地址，真实环境是存放在数据库中的
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/upload";
            path = URLDecoder.decode(path, "utf-8");
            File file = new File(path + File.separator + filename);
            // 创建输入流，传入文件对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;filename=" + filename);
            // 获取响应输出流
            OutputStream os = response.getOutputStream();
            //输入流复制给输出流
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            //释放资源
            os.close();
            fis.close();
        } catch (IOException e) {
            System.err.println("下载失败");
            return "redirect:/contracts";
        }
        return null;
    }
}

