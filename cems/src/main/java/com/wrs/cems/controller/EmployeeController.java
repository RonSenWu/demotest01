package com.wrs.cems.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrs.cems.bean.Department;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.bean.Nation;
import com.wrs.cems.bean.Position;
import com.wrs.cems.config.PaginationConstant;
import com.wrs.cems.service.IDeparmentService;
import com.wrs.cems.service.IEmployeeService;
import com.wrs.cems.service.INationService;
import com.wrs.cems.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDeparmentService deparmentService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private INationService nationService;


    // 查询所有员工返回列表页面
    @RequestMapping("/emps")
    public String list(HttpServletRequest request, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.CURRENT_NUM;
        }
        //设置分页(当前页，和每页显示数据条数)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        String url = request.getServletPath();
        model.addAttribute("url", url);
        // System.out.println("url="+url);

        //查找数据
        List<Employee> emps = employeeService.getEmpAndDept();
        //将查找出的结果封装到PageInfo对象中，这个对象包含了 emps集合和关于分页的方法，如下
        //pageInfo.getPageNum();
        //pageInfo.getPages(); 得到总页数
        //pageInfo.getNextPage(); 得到下一页
        //pageInfo.getPrePage(); 得到前一页
        PageInfo<Employee> pageInfo = new PageInfo<>(emps);
        // model.addAttribute("emps",emps);
        //用Model传递对象到page页面
        model.addAttribute("pageInfo", pageInfo);
        return "emp/list";
    }

    // 来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        /*Department dept = deparmentService.getDeptById(deptId);
        System.out.println("dept="+dept);
        model.addAttribute("dept",dept);*/

        // 来到添加页面，查出所有的部门，在页面显示
        List<Department> depts = deparmentService.getAllDepts();
        model.addAttribute("depts", depts);
        // 查出所有民族
        List<Nation> nations = nationService.getAllNations();
        model.addAttribute("nations", nations);
        // 查出所有职位
        List<Position> positions = positionService.getAllPositions();
        //List<Position> positions = positionService.getPosByDid(did);
        model.addAttribute("positions", positions);
        //System.out.println("positions="+positions);

        return "emp/add";
    }

    @ResponseBody
    @RequestMapping("/emp/pos")
    public List<Position> getPosList(Integer id) {
        //@PathVariable("did") Integer did,
        List<Position> posList = positionService.getPosByDid(id);
        //System.out.println("posList="+posList);
        return posList;
    }

    // 员工添加
    // SpringMVC 自动将请求参数和入参对象的属性进行一一绑定，要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee emp) {
//        System.out.println("保存的员工信息"+emp);
        // 保存员工
        employeeService.addEmp(emp);
        // 来到员工列表页面
        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    // 来到修改页面，查出当前员工，在页面显示
    @GetMapping("/emp/{EId}")
    public String toUpdatePage(@PathVariable("EId") Integer EId, Model model) {
        Employee emp = employeeService.getEmpById(EId);
        model.addAttribute("emp", emp);

        // 页面要显示所有的部门列表
        List<Department> depts = deparmentService.getAllDepts();
        model.addAttribute("depts", depts);
        // 查出所有民族
        List<Nation> nations = nationService.getAllNations();
        model.addAttribute("nations", nations);
        // 查出所有职位
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("positions", positions);
        // 回到修改页面（add是一个修改添加二合一的页面）
        return "emp/add";
    }

    // 员工修改，需要提交员工EId
    @PutMapping("/emp")
    public String updateEmp(Employee emp) {
//        System.out.println("修改的员工数据：" + emp);
        employeeService.updateEmp(emp);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{EId}")
    public String deleteEmpById(@PathVariable("EId") Integer EId) {
        Employee employee = employeeService.getEmpById(EId);
        //String ewid = employee.getWorkID();
        employeeService.deleteEmpById(EId);
        //employeeService.delecteEmpByWorkID(ewid);
        return "redirect:/emps";
    }


    // 查询某个员工
    //提交表单+传回前端的映射
    @RequestMapping("/search")
    public String queryEmp(String name, String workID, String deptname, String workDate,
                           Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        String url = "/search";
        model.addAttribute("url", url);
        //System.out.println("url="+url);
        model.addAttribute("name", name);
        model.addAttribute("workID", workID);
        model.addAttribute("deptname", deptname);
        model.addAttribute("workDate", workDate);

        if ((name.equals("") || name.length() == 0) &&
                (workID.equals("") || workID.length() == 0) &&
                (workDate.equals("") || workDate.length() == 0) && deptname.equals("")) {
            return "redirect:/emps";
        }

      /*  List<Department> depts = deparmentService.getAllDepts();
        model.addAttribute("depts",depts);*/
        //查找数据
        List<Employee> emps = employeeService.query(name, workID, deptname, workDate);
        //System.out.println(emps);

        if (emps.size()!=0){
            model.addAttribute("emps", emps);
        }else{
            String info ="查询不到该信息！";
            model.addAttribute("info",info);
        }
        //传回前端
        PageInfo<Employee> pageInfo = new PageInfo<>(emps);
        // model.addAttribute("emps",emps);
        //用Model传递对象到page页面
        model.addAttribute("pageInfo", pageInfo);
        return "emp/list";
    }


}
