package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.bean.Emprp;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IEmployeeService;
import com.wrs.cems.service.IEmprpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmprpController {
    @Autowired
    private IEmprpService emprpService;

    @Autowired
    private IEmployeeService employeeService;
    // 查询所有
    @RequestMapping("/emprps")
    public String list(HttpServletRequest request,Model model, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {

        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        String url =request.getServletPath();
        model.addAttribute("url",url);
        //查找数据
        List<Emprp> emprps = emprpService.getEmprpAndEmp();

        PageInfo<Emprp> pageInfo=new PageInfo<>(emprps);

        //用Model传递对象到page页面
        model.addAttribute("pageInfo",pageInfo);
        return "emprp/emprp";
    }

    // 来到添加页面
    @GetMapping("/emprp")
    public String toAddPage(Model model) {
        // 来到添加页面
        List<Employee> emps = employeeService.getAllEmps();
        model.addAttribute("emps",emps);

        return "emprp/add";
    }

    // 添加
    @PostMapping("/emprp")
    public String addEmprp(Emprp emprp) {
        // 保存员工
        emprpService.addEmprp(emprp);
        // 来到列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emprps";
    }

    // 来到修改页面
    @GetMapping("/emprp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Emprp emprp = emprpService.getEmprpById(id);
        model.addAttribute("emprp",emprp);

        //List<Employee> emps = employeeService.getAllEmps();
        String wid = emprp.getRpWorkId();
        Employee emps = employeeService.getEmpByWorkID(wid);
        model.addAttribute("emps",emps);

        // 回到修改页面（add是一个修改添加二合一的页面）
        return "emprp/add";
    }

    // 修改，需要提交id
    @PutMapping("/emprp")
    public String updateEmprp(Emprp emprp) {
        emprpService.updateEmprp(emprp);
        System.out.println(emprpService.updateEmprp(emprp));
        return "redirect:/emprps";
    }

    //删除
    @DeleteMapping("/emprp/{id}")
    public String deleteEmprpById(@PathVariable("id") Integer id){
        emprpService.deleteEmprpById(id);
        return "redirect:/emprps";
    }

    @RequestMapping("/rpsearch")//提交表单+传回前端的映射
    public String queryEmprp( String name, String rpWorkId, String rpType,Model model,
                              @RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        model.addAttribute("name",name);
        model.addAttribute("rpWorkId",rpWorkId);
        model.addAttribute("rpType",rpType);

        String url = "/rpsearch";
        model.addAttribute("url",url);

        if ((name.equals("")||name.length()==0)&&(rpWorkId.equals("")||rpWorkId.length()==0)&&rpType.equals("")){
            return "redirect:/emprps";
        }
        //查找数据
        List<Emprp> emprps = emprpService.query(name,rpWorkId,rpType);
        if (emprps.size() ==0){
            String info ="查询不到该信息！";
            model.addAttribute("info",info);
        }

        PageInfo<Emprp> pageInfo=new PageInfo<>(emprps);

        model.addAttribute("pageInfo",pageInfo);
        return "emprp/emprp";
    }
}
