package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.Result;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IOwnerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月07日 20:20
 */
// 必须 RESTful
@RestController
@RequestMapping("/owner")
public class OwnerInfoController {

    // 自动注入
    @Autowired
    private IOwnerInfoService ownerInfoService;

    // 添加业主信息 post请求
    @PostMapping
    public Result addOwner(OwnerInfo ownerInfo) {
        return Result.ok(ownerInfoService.save(ownerInfo));
    }

    // 修改业主信息 put请求
    @PutMapping
    public Result updateOwner(OwnerInfo ownerInfo) {
        return Result.ok(ownerInfoService.updateById(ownerInfo));
    }

    // 根据id删除业主信息 delete请求
    @DeleteMapping("/{id}")
    public Result delOwner(@PathVariable  Integer id) {
        return Result.ok(ownerInfoService.removeById(id));
    }

    // 单查 get请求
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        OwnerInfo ownerInfo = ownerInfoService.getById(id);
        if (ownerInfo == null) {
            return Result.fail("业主不存在");
        }
        return Result.ok(ownerInfo);
    }

    // 全查 get请求
    @GetMapping
    public Result queryAll() {
        List<OwnerInfo> ownerInfoList = ownerInfoService.list();
        return Result.ok(ownerInfoList);
    }



    @RequestMapping("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, OwnerInfo ownerInfo) {

        IPage<OwnerInfo> page = ownerInfoService.getPage(currentPage, pageSize, ownerInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = ownerInfoService.getPage((int) page.getPages(), pageSize, ownerInfo);
        }
        return Result.ok(page);
    }

}
