package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import edu.f4.pojo.EmployeeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

    EmployeeInfo getEmpAndRoleAndPermsByEmpNum(Integer empNum);

    EmployeeInfo queryByEmpNum(Integer empNum);

}
