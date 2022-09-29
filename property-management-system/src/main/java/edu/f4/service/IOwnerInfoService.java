package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.OwnerInfo;

public interface IOwnerInfoService extends IService<OwnerInfo> {

    IPage<OwnerInfo> getPage(int currentPage, int pageSize, OwnerInfo ownerInfo);

}
