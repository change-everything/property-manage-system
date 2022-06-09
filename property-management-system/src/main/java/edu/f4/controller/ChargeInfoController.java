package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.Result;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IChargeInfoService;
import edu.f4.service.IOwnerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charge")
public class ChargeInfoController {

    // 自动注入
    @Autowired
    private IChargeInfoService chargeInfoService;

    @PostMapping
    public Result addCharge(ChargeInfo chargeInfo) {
        return Result.ok(chargeInfoService.save(chargeInfo));
    }

    @PutMapping
    public Result updateCharge(ChargeInfo chargeInfo) {
        return Result.ok(chargeInfoService.updateById(chargeInfo));
    }

    @DeleteMapping("/{id}")
    public Result delCharge(@PathVariable  Integer id) {
        return Result.ok(chargeInfoService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        ChargeInfo chargeInfo = chargeInfoService.getById(id);
        if (chargeInfo == null) {
            return Result.fail("无收费项目");
        }
        return Result.ok(chargeInfo);
    }


    //分页
    @RequestMapping("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, ChargeInfo chargeInfo) {

        IPage<ChargeInfo> page = chargeInfoService.getPage(currentPage, pageSize, chargeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = chargeInfoService.getPage((int) page.getPages(), pageSize, chargeInfo);
        }
        return Result.ok(page);
    }


    // 按状态查询
    @GetMapping("/status/{status}")
    public Result queryByStatus(@PathVariable Integer status) {
        return Result.ok(chargeInfoService.queryChargeInfoByStatus(status));
    }

    @PutMapping("/status")
    public Result updateStatus(ChargeInfo chargeInfo) {
        return Result.ok(chargeInfoService);
    }

}
