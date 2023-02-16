package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.bean.Empsalary;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IEmployeeService;
import com.wrs.cems.service.IEmpsalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmpsalaryController {
    @Autowired
    IEmpsalaryService empsalaryService;

    @Autowired
    IEmployeeService employeeService;

    // 查询所有
    @RequestMapping("/salarys")
    public String list(HttpServletRequest request, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        String url = request.getServletPath();
        model.addAttribute("url", url);

        //查找数据
        List<Empsalary> empsalarys = empsalaryService.getAll();

        PageInfo<Empsalary> pageInfo = new PageInfo<>(empsalarys);

        //用Model传递对象到page页面
        model.addAttribute("pageInfo", pageInfo);
        return "salary/salary";
    }

    // 来到添加页面
    @GetMapping("/salary")
    public String toAddPage(Model model) {
        // 来到添加页面
        List<Employee> emps = employeeService.getAllEmps();
        model.addAttribute("emps", emps);

        List<Empsalary> empsalarys = empsalaryService.getAllEmpsalarys();
        model.addAttribute("empsalarys", empsalarys);

        return "salary/add";
    }

    // 添加
    @PostMapping("/salary")
    public String addEmpSalary(Empsalary empsalary) {
        // 保存员工
        empsalaryService.addEmpsalary(empsalary);
        // 来到列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/salarys";
    }

    // 来到修改页面
    @GetMapping("/salary/{SId}")
    public String toUpdatePage(@PathVariable("SId") Integer SId, Model model) {
        Empsalary empsalary = empsalaryService.getEmpsalaryById(SId);
        model.addAttribute("empsalary", empsalary);

        //List<Employee> emps = employeeService.getAllEmps();
        String wid = empsalary.getSWorkId();
        Employee emps = employeeService.getEmpByWorkID(wid);
        model.addAttribute("emps", emps);

        // 回到修改页面（add是一个修改添加二合一的页面）
        return "salary/add";
    }

    // 修改，需要提交SId
    @PutMapping("/salary")
    public String updateEmpsalary(Empsalary empsalary) {
        empsalaryService.updateEmpsalary(empsalary);
        return "redirect:/salarys";
    }

    //删除
    @DeleteMapping("/salary/{SId}")
    public String deleteEmpsalaryById(@PathVariable("SId") Integer SId) {
        empsalaryService.deleteEmpsalaryById(SId);
        return "redirect:/salarys";
    }


    @RequestMapping("/salsearch")//提交表单+传回前端的映射
    public String queryEmpsalary(String name, String SWorkId, String salDate, Model model,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        model.addAttribute("name", name);
        model.addAttribute("SWorkId", SWorkId);
        model.addAttribute("salDate", salDate);

        String url = "/salsearch";
        model.addAttribute("url", url);
        if ((name.equals("") || name.length() == 0) &&
                (SWorkId.equals("") || SWorkId.length() == 0) &&
                (salDate.equals("") || salDate.length() == 0)) {

            return "redirect:/contracts";
        }
        //查找数据
        List<Empsalary> empsalarys = empsalaryService.query(name, SWorkId, salDate);

        if (empsalarys.size()==0){
            String info ="查询不到该信息！";
            model.addAttribute("info",info);
        }

        PageInfo<Empsalary> pageInfo = new PageInfo<>(empsalarys);

        model.addAttribute("pageInfo", pageInfo);
        return "salary/salary";
    }
}