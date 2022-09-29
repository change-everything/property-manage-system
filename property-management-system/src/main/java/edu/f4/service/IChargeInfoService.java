package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.ChargeInfo;
import edu.f4.result.Result;

import java.util.List;

public interface IChargeInfoService extends IService<ChargeInfo> {
    IPage<ChargeInfo> getPage(int currentPage, int pageSize, ChargeInfo chargeInfo);



    Result getChargeByRoomNum(Integer roomNum);

}
