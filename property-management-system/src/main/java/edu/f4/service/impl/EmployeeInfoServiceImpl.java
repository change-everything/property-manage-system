package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.EmployeeDTO;
import edu.f4.dto.LoginFormDTO;
import edu.f4.result.Result;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo>implements IEmployeeInfoService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Override
    public IPage<EmployeeInfo> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo) {
        LambdaQueryWrapper<EmployeeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(employeeInfo.getAddress()), EmployeeInfo::getAddress, employeeInfo.getAddress());
        queryWrapper.like(Strings.isNotEmpty(employeeInfo.getEmpName()), EmployeeInfo::getEmpName, employeeInfo.getEmpName());

        IPage<EmployeeInfo> page = new Page<>(currentPage, pageSize);
        employeeInfoMapper.selectPage(page, queryWrapper);
        return page;
    }




}
