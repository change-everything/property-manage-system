package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.dto.NoPayDTO;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.NoPayChargeInfo;

/**
 * @author PeiYP
 * @date 2022年06月12日 22:27
 */
public interface INoPayChargeInfoService extends IService<NoPayChargeInfo> {

    boolean payTheFees(NoPayDTO noPayDTO);

    IPage<NoPayChargeInfo> getPage(int currentPage, int pageSize, NoPayChargeInfo chargeInfo);



}
