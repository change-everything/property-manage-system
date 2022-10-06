package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.dto.EmpLoginDTO;
import edu.f4.enumEntity.GenderEnum;
import edu.f4.enumEntity.JobEnum;
import edu.f4.result.Result;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

/**
 * @author PeiYP
 * @since 2022年06月07日 20:08
 */
@RestController
@RequestMapping("/emp")
public class EmployeeInfoController {

    @Autowired
    private IEmployeeInfoService employeeInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/{id}")
    public Result queryOne(@PathVariable Integer id) {
        EmpAndDeptDTO emp = employeeInfoService.getEmpAndDept(id);

        emp.setGender(Objects.requireNonNull(GenderEnum.getMessageByName(emp.getGender())).toString());

        return Result.ok(emp);
    }

    @PostMapping("/{roleId}")
    public Result addEmployee(@RequestBody EmployeeInfo employeeInfo, @PathVariable Integer roleId) {

        boolean save = employeeInfoService.addEmployee(employeeInfo, roleId);

        return Result.ok(save);
    }


    @PutMapping("/{roleId}")
    public Result updateEmployee(@RequestBody EmployeeInfo employeeInfo, @PathVariable Integer roleId) {


        boolean update = employeeInfoService.updateEmployee(employeeInfo, roleId);
        return Result.ok(update);
    }

    @DeleteMapping("/{id}")
    public Result delEmployee(@PathVariable Integer id) {
        boolean remove = employeeInfoService.removeById(id);

        return Result.ok(remove);
    }



    @PostMapping("/{currentPage}/{pageSize}")
    public Result selectPageVoLike(@PathVariable int currentPage, @PathVariable int pageSize, @RequestBody EmployeeInfo employeeInfo) {
        IPage<EmpAndDeptDTO> page = employeeInfoService.getPage(currentPage, pageSize, employeeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = employeeInfoService.getPage((int) page.getPages(), pageSize, employeeInfo);
        }
        return Result.ok(page);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, EmployeeInfo employeeInfo) {
        IPage<EmpAndDeptDTO> page = employeeInfoService.getPage(currentPage, pageSize, employeeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = employeeInfoService.getPage((int) page.getPages(), pageSize, employeeInfo);
        }
        return Result.ok(page);
    }


    //public static void main(String[] args) {
    //    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //    System.out.println(encoder.encode("root"));
    //}

}
