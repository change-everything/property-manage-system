package edu.f4.security;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.f4.dto.EmpLoginDTO;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.result.JsonResult;
import edu.f4.enumEntity.ResultCode;
import edu.f4.result.ResultTool;
import edu.f4.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月09日 12:16
 * 登陆成功
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private EmployeeInfoMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EmployeeInfo employeeInfo = mapper.selectOne(new QueryWrapper<EmployeeInfo>().eq("emp_num", userDetails.getUsername()));
        EmployeeInfo emp = mapper.getEmpAndRoleAndPermsByEmpNum(employeeInfo.getEmpNum());
        EmpLoginDTO empDTO = BeanUtil.copyProperties(emp, EmpLoginDTO.class);

        // 根据用户的id和account生成token并返回
        String jwtToken = JwtUtil.getJwtToken(employeeInfo.getEmpId().toString(), employeeInfo.getEmpNum().toString());
        //Map<String, String> results = new HashMap<>();
        //results.put("token", jwtToken);

        List<String> permList = new ArrayList<>();
        for (int i = 0; i < emp.getPerms().size(); i++) {
            permList.add(emp.getPerms().get(i).getPermCode());
        }

        empDTO.setToken(jwtToken);

        if (emp.getRole() != null) {
            empDTO.setRole(emp.getRole().getRoleName());
        }

        empDTO.setPermCode(permList);

        //返回json数据
        JsonResult result = ResultTool.success(ResultCode.SUCCESS_login, empDTO);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        // 把Json数据放入HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
