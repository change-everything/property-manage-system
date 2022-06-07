package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.ChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import edu.f4.service.IChargeInfoService;
import org.springframework.stereotype.Service;

@Service
public class ChargeInfoServiceImpl extends ServiceImpl<ChargeInfoMapper, ChargeInfo> implements IChargeInfoService {
}
