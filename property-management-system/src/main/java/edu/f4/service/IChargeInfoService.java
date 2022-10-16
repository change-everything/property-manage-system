package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.ChargeInfo;
import edu.f4.result.Result;

import java.util.List;
import java.util.Map;

public interface IChargeInfoService extends IService<ChargeInfo> {
    IPage<ChargeInfo> getPage(int currentPage, int pageSize, ChargeInfo chargeInfo);



    Result getChargeByRoomNum(Integer roomNum);

    /**
     * 获取账单统计表数据
     */
    Map<String, Object> getChargeInfo();

}
