package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.DepartmentInfoMapper;
import edu.f4.pojo.DepartmentInfo;
import edu.f4.service.IDepartmentInfoService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentInfoServiceImpl extends ServiceImpl<DepartmentInfoMapper, DepartmentInfo> implements IDepartmentInfoService {
}
