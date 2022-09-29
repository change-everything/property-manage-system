package edu.f4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.OwnerInfoMapper;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IOwnerInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerInfoServiceImpl extends ServiceImpl<OwnerInfoMapper, OwnerInfo>implements IOwnerInfoService {

    @Autowired
    private OwnerInfoMapper ownerInfoMapper;

    @Override
    public IPage<OwnerInfo> getPage(int currentPage, int pageSize, OwnerInfo ownerInfo) {
        LambdaQueryWrapper<OwnerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(ownerInfo.getOwnerName()), OwnerInfo::getOwnerName, ownerInfo.getOwnerName());
        queryWrapper.like(ownerInfo.getOwnerNum() != null, OwnerInfo::getOwnerNum, ownerInfo.getOwnerNum());
        queryWrapper.like(ownerInfo.getRoomNum() != null, OwnerInfo::getRoomNum, ownerInfo.getRoomNum());

        IPage<OwnerInfo> page = new Page<>(currentPage, pageSize);
        ownerInfoMapper.selectPage(page, queryWrapper);
        return page;
    }
}
