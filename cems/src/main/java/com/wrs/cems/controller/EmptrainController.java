package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrs.cems.bean.Department;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.bean.Emptrain;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IDeparmentService;
import com.wrs.cems.service.IEmployeeService;
import com.wrs.cems.service.IEmptrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmptrainController {
    @Autowired
    private IEmptrainService emptrainService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDeparmentService deparmentService;

    // 查询所有
    @RequestMapping("/trains")
    public String list(HttpServletRequest request, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        String url = request.getServletPath();
        model.addAttribute("url", url);

        //查找数据
        List<Emptrain> emptrains = emptrainService.getEmptrainAndEmp();

        PageInfo<Emptrain> pageInfo = new PageInfo<>(emptrains);

        //用Model传递对象到page页面
        model.addAttribute("pageInfo", pageInfo);
        return "train/train";
    }

    // 来到添加页面
    @GetMapping("/train")
    public String toAddPage(Model model) {
        // 来到添加页面
        List<Employee> emps = employeeService.getAllEmps();
        model.addAttribute("emps", emps);

        List<Department> depts = deparmentService.getAllDepts();
        model.addAttribute("depts", depts);

        return "train/add";
    }

    // 添加
    @PostMapping("/train")
    public String addEmptrain(Emptrain emptrain) {
        // 保存员工
        emptrainService.addEmptrain(emptrain);
        // 来到列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/trains";
    }

    // 来到修改页面
    @GetMapping("/train/{TId}")
    public String toUpdatePage(@PathVariable("TId") Integer TId, Model model) {
        Emptrain emptrain = emptrainService.getEmptrainById(TId);
        model.addAttribute("train", emptrain);

        List<Department> depts = deparmentService.getAllDepts();
        model.addAttribute("depts", depts);

        //List<Employee> emps = employeeService.getAllEmps();
        String wid = emptrain.getTWorkId();
        Employee emps = employeeService.getEmpByWorkID(wid);
        model.addAttribute("emps", emps);

        // 回到修改页面（add是一个修改添加二合一的页面）
        return "train/add";
    }

    // 修改，需要提交TId
    @PutMapping("/train")
    public String updateEmptrain(Emptrain emptrain) {
        emptrainService.updateEmptrain(emptrain);
        return "redirect:/trains";
    }

    //删除
    @DeleteMapping("/train/{TId}")
    public String deleteEmptrainById(@PathVariable("TId") Integer TId) {
        emptrainService.deleteEmptrainById(TId);
        return "redirect:/trains";
    }

    @RequestMapping("/trasearch")//提交表单+传回前端的映射
    public String queryEmptrain( String name, String TWorkId,String deptname, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        model.addAttribute("name", name);
        model.addAttribute("TWorkId", TWorkId);
        model.addAttribute("deptname", deptname);
        if ((name.equals("")||name.length()==0)&&(TWorkId.equals("")||TWorkId.length()==0)&&deptname.equals("")){
            return "redirect:/trains";
        }
        String url = "/trasearch";
        model.addAttribute("url", url);
        //查找数据
        List<Emptrain> emptrains = emptrainService.query(name,TWorkId,deptname);

        if (emptrains.size()==0){
            String info ="查询不到该信息！";
            model.addAttribute("info",info);
        }

        PageInfo<Emptrain> pageInfo = new PageInfo<>(emptrains);

        model.addAttribute("pageInfo", pageInfo);
        return "train/train";
    }
}
