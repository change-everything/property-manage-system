package edu.f4.controller;

import edu.f4.dto.LoginFormDTO;
import edu.f4.dto.Result;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author PeiYP
 * @since 2022年06月08日 10:57
 */
@RestController
@RequestMapping
public class LoginController {


    @Autowired
    private IEmployeeInfoService employeeInfoService;

    @RequestMapping("/login")
    public Result Login(@RequestBody LoginFormDTO loginFormDTO, HttpSession session) {

        return employeeInfoService.login(loginFormDTO, session);

    }

}
