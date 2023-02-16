package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrs.cems.bean.Assessment;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IAssessmentService;
import com.wrs.cems.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AssessmentController {
    @Autowired
    private IAssessmentService assessmentService;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/appraises")
    public String list(HttpServletRequest request, Model model, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        String url = request.getServletPath();
        model.addAttribute("url",url);

        //查找数据
        List<Assessment> assessments = assessmentService.getAll();
        //将查找出的结果封装到PageInfo对象中，这个对象包含了 emps集合和关于分页的方法，如下
        //pageInfo.getPageNum();
        //pageInfo.getPages(); 得到总页数
        //pageInfo.getNextPage(); 得到下一页
        //pageInfo.getPrePage(); 得到前一页
        PageInfo<Assessment> pageInfo=new PageInfo<>(assessments);
        //用Model传递对象到page页面
        model.addAttribute("pageInfo",pageInfo);
        return "appraise/appraise";
    }

    // 来到添加页面
    @GetMapping("/appraise")
    public String toAddPage(Model model) {
        // 来到添加页面
        List<Employee> emps = employeeService.getAllEmps();
        model.addAttribute("emps",emps);

        return "appraise/add";
    }

    // 添加
    // SpringMVC 自动将请求参数和入参对象的属性进行一一绑定，要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/appraise")
    public String addAppraise(Assessment assessment) {
        // 保存
        assessmentService.addAssessment(assessment);
        // 来到列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/appraises";
    }

    // 来到修改页面
    @GetMapping("/appraise/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Assessment assessment = assessmentService.getAssessmentById(id);
        String wid = assessment.getEid();
        model.addAttribute("appraise",assessment);

        /*List<Employee> emps = employeeService.getAllEmps();*/
        Employee emps=employeeService.getEmpByWorkID(wid);

        model.addAttribute("emps",emps);

        // 回到修改页面（add是一个修改添加二合一的页面）
        return "appraise/add";
    }

    // 修改，需要提交id
    @PutMapping("/appraise")
    public String updateAppraise(Assessment assessment) {
        assessmentService.updateAssessment(assessment);
        return "redirect:/appraises";
    }

    //删除
    @DeleteMapping("/appraise/{id}")
    public String deleteAppraiseById(@PathVariable("id") Integer id){
        assessmentService.deleteAssessmentById(id);
        return "redirect:/appraises";
    }

    @RequestMapping("/appsearch")//提交表单+传回前端的映射
    public String queryAppraise(@RequestParam(value = "name",required = false) String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //查找数据
        List<Assessment> appraises = assessmentService.query(name);

        model.addAttribute("name",name);
        String url="/appsearch";
        model.addAttribute("url",url);
        PageInfo<Assessment> pageInfo=new PageInfo<>(appraises);

        model.addAttribute("pageInfo",pageInfo);
        return "appraise/appraise";
    }
}
