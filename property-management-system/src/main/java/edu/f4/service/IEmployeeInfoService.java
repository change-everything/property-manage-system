package edu.f4.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.dto.LoginFormDTO;
import edu.f4.result.Result;
import edu.f4.pojo.EmployeeInfo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IEmployeeInfoService extends IService<EmployeeInfo> {

    IPage<EmpAndDeptDTO> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo);


    EmployeeInfo getEmpAndDept(Integer empId);

    boolean addEmployee(EmployeeInfo employeeInfo, Integer roleId);

    boolean updateEmployee(EmployeeInfo employeeInfo, Integer roleId);


}
