package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.dto.LoginFormDTO;
import edu.f4.dto.Result;
import edu.f4.pojo.EmployeeInfo;

import javax.servlet.http.HttpSession;

public interface IEmployeeInfoService extends IService<EmployeeInfo> {

    IPage<EmployeeInfo> getPage(int currentPage, int pageSize, EmployeeInfo employeeInfo);

    Result login(LoginFormDTO loginFormDTO, HttpSession session);
}
