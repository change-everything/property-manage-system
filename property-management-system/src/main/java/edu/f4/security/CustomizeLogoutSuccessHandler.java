package edu.f4.security;

import com.alibaba.fastjson.JSON;
import edu.f4.result.JsonResult;
import edu.f4.enumEntity.ResultCode;
import edu.f4.result.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PeiYP
 * @since 2022年06月09日 12:41
 * 退出登录
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JsonResult result = ResultTool.success(ResultCode.SUCCESS_logout);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
