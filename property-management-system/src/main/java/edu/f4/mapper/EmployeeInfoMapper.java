package edu.f4.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fasterxml.jackson.databind.ser.Serializers;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.pojo.EmployeeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

    EmployeeInfo getEmpAndRoleAndPermsByEmpNum(Integer empNum);

    EmployeeInfo queryByEmpNum(Integer empNum);

    IPage<EmpAndDeptDTO> queryEmpAndDeptLike(IPage<EmployeeInfo> page, @Param(Constants.WRAPPER) QueryWrapper<EmployeeInfo> wrapper);

    EmployeeInfo getEmpAndDept(Integer empId);

    EmployeeInfo getEmpRole(Integer empId);

    boolean insertEmpConnRole(Integer empId, Integer roleId);

    boolean updateEmpRole(Integer empId, Integer roleId);




}
