package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.OwnerInfo;
import edu.f4.result.Result;
import edu.f4.service.IChargeInfoService;
import edu.f4.service.IOwnerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/charge")
public class ChargeInfoController {

    // 自动注入
    @Autowired
    private IChargeInfoService chargeInfoService;


    @GetMapping("/info")
    public Result getInfo() {
        Map<String, Object> map = chargeInfoService.getChargeInfo();
        return Result.ok(map);
    }

    @PostMapping
    public Result addCharge(@RequestBody ChargeInfo chargeInfo) {
        return Result.ok(chargeInfoService.save(chargeInfo));
    }

    //分页
    @GetMapping ("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, ChargeInfo chargeInfo) {

        IPage<ChargeInfo> page = chargeInfoService.getPage(currentPage, pageSize, chargeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = chargeInfoService.getPage((int) page.getPages(), pageSize, chargeInfo);
        }
        return Result.ok(page);
    }

    @PostMapping ("/{currentPage}/{pageSize}")
    public Result selectPageVoLike(@PathVariable int currentPage, @PathVariable int pageSize, @RequestBody ChargeInfo chargeInfo) {

        IPage<ChargeInfo> page = chargeInfoService.getPage(currentPage, pageSize, chargeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = chargeInfoService.getPage((int) page.getPages(), pageSize, chargeInfo);
        }
        return Result.ok(page);
    }

    @GetMapping("/bill/{roomNum}")
    public Result getChargeByRoomNum(@PathVariable Integer roomNum) {
        return chargeInfoService.getChargeByRoomNum(roomNum);
    }


}
