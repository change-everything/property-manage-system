package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.ChargeDTO;
import edu.f4.dto.ChargeInfoVo;
import edu.f4.mapper.ChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.result.Result;
import edu.f4.service.IChargeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChargeInfoServiceImpl extends ServiceImpl<ChargeInfoMapper, ChargeInfo> implements IChargeInfoService {

    @Autowired
    private ChargeInfoMapper chargeInfoMapper;

    @Override
    public IPage<ChargeInfo> getPage(int currentPage, int pageSize, ChargeInfo chargeInfo) {

        LambdaQueryWrapper<ChargeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(chargeInfo.getEmpNum() != null, ChargeInfo::getEmpNum, chargeInfo.getEmpNum());
        queryWrapper.like(chargeInfo.getOwnerNum() != null, ChargeInfo::getOwnerNum, chargeInfo.getOwnerNum());
        queryWrapper.like(chargeInfo.getRoomNum() != null, ChargeInfo::getRoomNum, chargeInfo.getRoomNum());

        IPage<ChargeInfo> page = new Page<>(currentPage, pageSize * 100L);
        chargeInfoMapper.selectPage(page, queryWrapper);

        List<ChargeInfo> records = page.getRecords();
        Map<String, ChargeInfoVo> chargeInfoVos = new HashMap<>();
        List<ChargeInfo> chargeInfos = new ArrayList<>();

        for (ChargeInfo record : records) {
            List<ChargeInfo> infos = query().eq("room_num", record.getRoomNum()).list();

            for (ChargeInfo info : infos) {
                ChargeInfoVo chargeInfoVo = BeanUtil.copyProperties(info, ChargeInfoVo.class);
                chargeInfoVos.put(chargeInfoVo.getRoomNum().toString(), chargeInfoVo);
            }

        }

        Collection<ChargeInfoVo> values = chargeInfoVos.values();


        ArrayList<ChargeInfoVo> vos = new ArrayList<>(values);

        for (ChargeInfoVo vo : vos) {

            ChargeInfo info = BeanUtil.copyProperties(vo, ChargeInfo.class);

            chargeInfos.add(info);

        }

        page.setRecords(chargeInfos);
        page.setTotal(vos.size());

        return page;
    }


    @Override
    public Result getChargeByRoomNum(Integer roomNum) {

        List<ChargeInfo> chargeInfos = chargeInfoMapper.getChargeByRoomNum(roomNum);

        List<ChargeDTO> chargeDTOS = new ArrayList<>();

        for (ChargeInfo chargeInfo : chargeInfos) {
            ChargeDTO chargeDTO = BeanUtil.copyProperties(chargeInfo, ChargeDTO.class);
            chargeDTO.setOwnerName(chargeInfo.getOwnerInfo().getOwnerName());
            chargeDTOS.add(chargeDTO);
        }

        return Result.ok(chargeDTOS);
    }


}
