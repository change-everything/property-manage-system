package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.ChargeDTO;
import edu.f4.mapper.ChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.result.Result;
import edu.f4.service.IChargeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public boolean updateStatus(Integer chaId) {
        return chargeInfoMapper.updateStatusById(chaId);
    }

    @Override
    public Result getChargeByRoomNum(Integer roomNum) {

        List<ChargeInfo> chargeInfos = chargeInfoMapper.getChargeByRoomNum(roomNum);

        List<ChargeDTO> chargeDTOS = new ArrayList<>();

        for (int i = 0; i < chargeInfos.size(); i++) {
            ChargeDTO chargeDTO = BeanUtil.copyProperties(chargeInfos.get(i), ChargeDTO.class);
            chargeDTO.setOwnerName(chargeInfos.get(i).getOwnerInfo().getOwnerName());
            chargeDTOS.add(chargeDTO);
        }

        return Result.ok(chargeDTOS);
    }


}
