package edu.f4.controller;

import edu.f4.pojo.SystemParam;
import edu.f4.result.Result;
import edu.f4.service.ISystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PeiYP
 * @since 2022年09月29日 20:56
 */
@RestController
@RequestMapping("/sys")
public class SystemParamController {

    @Autowired
    private ISystemParamService systemParamService;

    @GetMapping
    public Result queryAll() {
        return Result.ok(systemParamService.list());
    }

    @PostMapping
    public Result addParam(@RequestBody SystemParam systemParam) {
        return Result.ok(systemParamService.save(systemParam) ? "添加成功" : "添加失败");
    }

    @PutMapping
    public Result updateParam(@RequestBody SystemParam systemParam) {
        return Result.ok(systemParamService.updateById(systemParam) ? "修改成功" : "修改失败");
    }

    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id) {
        return Result.ok(systemParamService.removeById(id) ? "删除成功" : "删除失败");
    }

    @GetMapping("/{id}")
    public Result queryOne(@PathVariable Integer id) {
        return Result.ok(systemParamService.getById(id));
    }

}
