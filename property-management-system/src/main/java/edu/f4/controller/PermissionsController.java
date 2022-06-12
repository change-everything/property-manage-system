package edu.f4.controller;

import edu.f4.pojo.Permissions;
import edu.f4.result.Result;
import edu.f4.service.IDepartmentInfoService;
import edu.f4.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perm")
public class PermissionsController {

    // 自动注入
    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping
    public Result addPerm(@RequestBody Permissions permissions) {
        return Result.ok(permissionsService.save(permissions));
    }

    @PutMapping
    public Result updatePerm(@RequestBody Permissions permissions) {
        return Result.ok(permissionsService.updateById(permissions));
    }

    @DeleteMapping("/{id}")
    public Result delPerm(@PathVariable  Integer id) {
        return Result.ok(permissionsService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        Permissions permissions = permissionsService.getById(id);
        if (permissions == null) {
            return Result.fail("无效权限");
        }
        return Result.ok(permissions);
    }

    @GetMapping
    public Result queryAll() {
        List<Permissions> permissionsList = permissionsService.list();
        return Result.ok(permissionsList);
    }

}
