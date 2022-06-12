package edu.f4.security;

import com.alibaba.fastjson.JSON;
import edu.f4.result.JsonResult;
import edu.f4.enumEntity.ResultCode;
import edu.f4.result.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PeiYP
 * @since 2022年06月09日 11:35
 * 未登录
 */

@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 返回的是Json数据
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
