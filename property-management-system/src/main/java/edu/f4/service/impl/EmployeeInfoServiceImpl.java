package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.dto.EmployeeDTO;
import edu.f4.dto.LoginFormDTO;
import edu.f4.enumEntity.DepartmentEnum;
import edu.f4.result.Result;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo>implements IEmployeeInfoService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;


    //@Override
    //public IPage<EmployeeInfo> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo) {
    //    LambdaQueryWrapper<EmployeeInfo> queryWrapper = new LambdaQueryWrapper<>();
    //    queryWrapper.like(employeeInfo.getEmpNum() != null, EmployeeInfo::getEmpNum, employeeInfo.getEmpNum());
    //    queryWrapper.like(Strings.isNotEmpty(employeeInfo.getPhone()), EmployeeInfo::getPhone, employeeInfo.getPhone());
    //    queryWrapper.like(employeeInfo.getEmpDepNum() != null, EmployeeInfo::getEmpDepNum, employeeInfo.getEmpDepNum());
    //
    //    IPage<EmployeeInfo> page = new Page<>(currentPage, pageSize);
    //    IPage<EmpAndDeptDTO> page1 = new Page<>(currentPage, pageSize);
    //    employeeInfoMapper.selectPage(page, queryWrapper);
    //    return page;
    //}
    @Override
    public IPage<EmpAndDeptDTO> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo) {
        LambdaQueryWrapper<EmployeeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(employeeInfo.getEmpNum() != null, EmployeeInfo::getEmpNum, employeeInfo.getEmpNum());
        queryWrapper.like(Strings.isNotEmpty(employeeInfo.getPhone()), EmployeeInfo::getPhone, employeeInfo.getPhone());
        queryWrapper.like(employeeInfo.getEmpDepNum() != null, EmployeeInfo::getEmpDepNum, employeeInfo.getEmpDepNum());

        IPage<EmployeeInfo> page = new Page<>(currentPage, pageSize);
        IPage<EmpAndDeptDTO> page1 = new Page<>(currentPage, pageSize);
        page1.setPages(page.getPages());
        page1.setCurrent(page.getCurrent());
        page1.setSize(page.getSize());
        page1.setTotal(page.getTotal());
        employeeInfoMapper.selectPage(page, queryWrapper);

        List<EmployeeInfo> records = page.getRecords();
        ArrayList<EmpAndDeptDTO> empAndDept = new ArrayList<>();
        for (EmployeeInfo info : records) {
            EmpAndDeptDTO empAndDeptDTO = BeanUtil.copyProperties(info, EmpAndDeptDTO.class);
            empAndDeptDTO.setEmpDepName(DepartmentEnum.getMessageByCode(info.getEmpDepNum().toString()));
            empAndDept.add(empAndDeptDTO);
        }

        page1.setRecords(empAndDept);
        return page1;
    }






}
