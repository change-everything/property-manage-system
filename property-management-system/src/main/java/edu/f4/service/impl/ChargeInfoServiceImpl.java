package edu.f4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.ChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IChargeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeInfoServiceImpl extends ServiceImpl<ChargeInfoMapper, ChargeInfo> implements IChargeInfoService {

    @Autowired
    private ChargeInfoMapper chargeInfoMapper;

    @Override
    public IPage<ChargeInfo> getPage(int currentPage, int pageSize, ChargeInfo chargeInfo) {

        IPage<ChargeInfo> page = new Page<>(currentPage, pageSize);
        chargeInfoMapper.selectPage(page, null);
        return page;
    }

    @Override
    public List<ChargeInfo> queryChargeInfoByStatus(Integer status) {
        return chargeInfoMapper.queryChargeInfoByStatus(status);
    }

    @Override
    public boolean updateStatus(ChargeInfo chargeInfo) {
        return chargeInfoMapper.updateStatus(chargeInfo);
    }


}
