package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.enumEntity.GenderEnum;
import edu.f4.enumEntity.JobEnum;
import edu.f4.result.Result;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        EmployeeInfo emp = employeeInfoService.getById(id);

        return Result.ok(emp);
    }

    @PostMapping
    public Result addEmployee(@RequestBody EmployeeInfo employeeInfo) {

        employeeInfo.setEmpPwd(passwordEncoder.encode(employeeInfo.getEmpPwd()));

        employeeInfo.setGender(GenderEnum.getMessageByCode(employeeInfo.getGender()));
        employeeInfo.setJob(JobEnum.getMessageByCode(employeeInfo.getJob()));

        boolean save = employeeInfoService.save(employeeInfo);

        return Result.ok(save);
    }


    @PutMapping
    public Result updateEmployee(@RequestBody EmployeeInfo employeeInfo) {
        employeeInfo.setEmpPwd(passwordEncoder.encode(employeeInfo.getEmpPwd()));

        employeeInfo.setGender(GenderEnum.getMessageByCode(employeeInfo.getGender()));
        employeeInfo.setJob(JobEnum.getMessageByCode(employeeInfo.getJob()));

        boolean update = employeeInfoService.updateById(employeeInfo);
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

}
