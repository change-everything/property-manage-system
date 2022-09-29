package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.dto.EmployeeDTO;
import edu.f4.dto.LoginFormDTO;
import edu.f4.enumEntity.DepartmentEnum;
import edu.f4.enumEntity.GenderEnum;
import edu.f4.pojo.RoleGroup;
import edu.f4.result.Result;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo>implements IEmployeeInfoService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public IPage<EmpAndDeptDTO> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo) {

        LambdaQueryWrapper<EmployeeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(employeeInfo.getEmpNum() != null, EmployeeInfo::getEmpNum, employeeInfo.getEmpNum());
        queryWrapper.like(Strings.isNotEmpty(employeeInfo.getPhone()), EmployeeInfo::getPhone, employeeInfo.getPhone());
        queryWrapper.like(employeeInfo.getEmpDepNum() != null, EmployeeInfo::getEmpDepNum, employeeInfo.getEmpDepNum());


        IPage<EmployeeInfo> page = new Page<>(currentPage, pageSize);
        employeeInfoMapper.selectPage(page, queryWrapper);
        IPage<EmpAndDeptDTO> page1 = new Page<>(currentPage, pageSize);
        page1.setPages(page.getPages());
        page1.setCurrent(page.getCurrent());
        page1.setSize(page.getSize());
        page1.setTotal(page.getTotal());

        List<EmployeeInfo> records = page.getRecords();

        ArrayList<EmpAndDeptDTO> empAndDept = new ArrayList<>();
        for (EmployeeInfo info : records) {

            EmployeeInfo empRole = employeeInfoMapper.getEmpRole(info.getEmpId());

            EmpAndDeptDTO empAndDeptDTO = BeanUtil.copyProperties(info, EmpAndDeptDTO.class);
            empAndDeptDTO.setEmpDepName(DepartmentEnum.getMessageByCode(info.getEmpDepNum().toString()));
            if (empRole.getRole() != null) {
                empAndDeptDTO.setRoleDest(empRole.getRole().getRoleDest());
            }

            empAndDept.add(empAndDeptDTO);
        }

        page1.setRecords(empAndDept);
        return page1;
    }

    @Override
    public EmpAndDeptDTO getEmpAndDept(Integer empId) {

        EmployeeInfo info = employeeInfoMapper.getEmpAndDept(empId);
        EmployeeInfo empRole = employeeInfoMapper.getEmpRole(info.getEmpId());
        EmpAndDeptDTO empAndDeptDTO = BeanUtil.copyProperties(info, EmpAndDeptDTO.class);

        RoleGroup role = empRole.getRole();
        if (role != null) {
            empAndDeptDTO.setRoleId(role.getRoleId());
        }
        return empAndDeptDTO;

    }


    @Transactional
    @Override
    public boolean addEmployee(EmployeeInfo employeeInfo, Integer roleId) {

        setInfo(employeeInfo);

        save(employeeInfo);

        return employeeInfoMapper.insertEmpConnRole(employeeInfo.getEmpId(), roleId);
    }

    @Override
    public boolean updateEmployee(EmployeeInfo employeeInfo, Integer roleId) {

        setInfo(employeeInfo);

        updateById(employeeInfo);

        return employeeInfoMapper.updateEmpRole(employeeInfo.getEmpId(), roleId);
    }

    @Override
    public List<EmployeeInfo> findEmpByDeptNum(Integer deptNum) {

        return query().eq("emp_dep_num", deptNum).list();

    }

    private void setInfo(EmployeeInfo employeeInfo) {
        employeeInfo.setEmpPwd(passwordEncoder.encode(employeeInfo.getEmpPwd()));

        employeeInfo.setGender(GenderEnum.getMessageByCode(employeeInfo.getGender()));
        String[] ts = employeeInfo.getBirthDate().split("T");
        employeeInfo.setBirthDate(ts[0]);
    }


}
