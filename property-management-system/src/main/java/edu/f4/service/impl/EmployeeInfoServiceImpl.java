package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo>implements IEmployeeInfoService {
}
