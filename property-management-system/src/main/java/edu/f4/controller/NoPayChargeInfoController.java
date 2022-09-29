package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.NoPayDTO;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.NoPayChargeInfo;
import edu.f4.result.Result;
import edu.f4.service.INoPayChargeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PeiYP
 * @since 2022年06月12日 22:48
 */
@RestController
@RequestMapping("/noPayCharge")
public class NoPayChargeInfoController {

    @Autowired
    private INoPayChargeInfoService noPayChargeInfoService;

    @PostMapping
    public Result addCharge(@RequestBody NoPayChargeInfo noPayChargeInfo) {
        return Result.ok(noPayChargeInfoService.save(noPayChargeInfo));
    }

    //分页
    @GetMapping("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, NoPayChargeInfo noPayChargeInfo) {

        IPage<NoPayChargeInfo> page = noPayChargeInfoService.getPage(currentPage, pageSize, noPayChargeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = noPayChargeInfoService.getPage((int) page.getPages(), pageSize, noPayChargeInfo);
        }
        return Result.ok(page);
    }

    @PostMapping ("/{currentPage}/{pageSize}")
    public Result selectPageVoLike(@PathVariable int currentPage, @PathVariable int pageSize, @RequestBody NoPayChargeInfo noPayChargeInfo) {

        IPage<NoPayChargeInfo> page = noPayChargeInfoService.getPage(currentPage, pageSize, noPayChargeInfo);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = noPayChargeInfoService.getPage((int) page.getPages(), pageSize, noPayChargeInfo);
        }
        return Result.ok(page);
    }

    @PostMapping("/pay")
    public Result payTheFees(@RequestBody NoPayDTO noPayDTO) {

        boolean b = noPayChargeInfoService.payTheFees(noPayDTO);

        return Result.ok(b);
    }


}
