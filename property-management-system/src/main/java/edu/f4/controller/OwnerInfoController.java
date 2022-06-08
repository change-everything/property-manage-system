package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.Result;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IOwnerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PeiYP
 * @since 2022年06月07日 20:20
 */
@RestController
@RequestMapping("/owner")
public class OwnerInfoController {

    @Autowired
    private IOwnerInfoService ownerInfoService;

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
