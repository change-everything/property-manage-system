package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.OwnerInfoMapper;
import edu.f4.pojo.OwnerInfo;
import edu.f4.service.IOwnerInfoService;
import org.springframework.stereotype.Service;

@Service
public class OwnerInfoServiceImpl extends ServiceImpl<OwnerInfoMapper, OwnerInfo>implements IOwnerInfoService {
}
