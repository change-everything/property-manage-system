package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.Result;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.DepartmentInfo;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IChargeInfoService;
import edu.f4.service.IDepartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentInfoController {

    // 自动注入
    @Autowired
    private IDepartmentInfoService departmentInfoService;

    @PostMapping
    public Result addDept(DepartmentInfo departmentInfo) {
        return Result.ok(departmentInfoService.save(departmentInfo));
    }

    @PutMapping
    public Result updateDept(DepartmentInfo departmentInfo) {
        return Result.ok(departmentInfoService.updateById(departmentInfo));
    }

    @DeleteMapping("/{id}")
    public Result delDept(@PathVariable  Integer id) {
        return Result.ok(departmentInfoService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        DepartmentInfo departmentInfo = departmentInfoService.getById(id);
        if (departmentInfo == null) {
            return Result.fail("不存在此部门信息");
        }
        return Result.ok(departmentInfo);
    }

    @GetMapping
    public Result queryAll() {
        List<DepartmentInfo> departmentInfoList = departmentInfoService.list();
        return Result.ok(departmentInfoList);
    }

}
