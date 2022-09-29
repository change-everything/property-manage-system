package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.ChargeInfoVo;
import edu.f4.dto.NoPayDTO;
import edu.f4.mapper.NoPayChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.NoPayChargeInfo;
import edu.f4.service.IChargeInfoService;
import edu.f4.service.INoPayChargeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author PeiYP
 * @since 2022年06月12日 22:29
 */
@Service
public class NoPayChargeInfoServiceImpl extends ServiceImpl<NoPayChargeInfoMapper, NoPayChargeInfo> implements INoPayChargeInfoService {

    @Autowired
    private NoPayChargeInfoMapper noPayChargeInfoMapper;

    @Autowired
    private IChargeInfoService chargeInfoService;


    @Transactional
    @Override
    public boolean payTheFees(NoPayDTO noPayDTO) {

        List<NoPayChargeInfo> infos = query().eq("room_num", noPayDTO.getRoomNum()).list();

        if (infos == null) {
            return false;
        }

        List<ChargeInfo> chargeInfos = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        for (NoPayChargeInfo info : infos) {

            ids.add(info.getChaId());

            ChargeInfo chargeInfo = BeanUtil.copyProperties(info, ChargeInfo.class);
            chargeInfo.setRoomNum(noPayDTO.getRoomNum());
            chargeInfo.setChaId(null);
            chargeInfo.setChaDate(time.format(date));

            chargeInfo.setEmpNum(noPayDTO.getEmpNum());

            chargeInfos.add(chargeInfo);

        }

        chargeInfoService.saveBatch(chargeInfos);

        return removeByIds(ids);

    }

    @Override
    public IPage<NoPayChargeInfo> getPage(int currentPage, int pageSize, NoPayChargeInfo chargeInfo) {

        LambdaQueryWrapper<NoPayChargeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(chargeInfo.getOwnerNum() != null, NoPayChargeInfo::getOwnerNum, chargeInfo.getOwnerNum());
        queryWrapper.like(chargeInfo.getRoomNum() != null, NoPayChargeInfo::getRoomNum, chargeInfo.getRoomNum());

        IPage<NoPayChargeInfo> page = new Page<>(currentPage, pageSize * 100L);
        noPayChargeInfoMapper.selectPage(page, queryWrapper);

        List<NoPayChargeInfo> records = page.getRecords();
        Map<String, ChargeInfoVo> chargeInfoVos = new HashMap<>();
        List<NoPayChargeInfo> chargeInfos = new ArrayList<>();

        for (NoPayChargeInfo record : records) {
            List<NoPayChargeInfo> infos = query().eq("room_num", record.getRoomNum()).list();

            for (NoPayChargeInfo info : infos) {
                ChargeInfoVo chargeInfoVo = BeanUtil.copyProperties(info, ChargeInfoVo.class);
                chargeInfoVos.put(chargeInfoVo.getRoomNum().toString(), chargeInfoVo);
            }

            Set<String> keySet = chargeInfoVos.keySet();

            for (String s : keySet) {
                ChargeInfoVo chargeInfoVo = chargeInfoVos.get(s);
                chargeInfoVo.setChildren(infos);
            }

        }

        Collection<ChargeInfoVo> values = chargeInfoVos.values();


        ArrayList<ChargeInfoVo> vos = new ArrayList<>(values);

        for (ChargeInfoVo vo : vos) {

            NoPayChargeInfo info = BeanUtil.copyProperties(vo, NoPayChargeInfo.class);

            chargeInfos.add(info);

        }

        page.setTotal(vos.size());

        page.setRecords(chargeInfos);

        return page;
    }

}
