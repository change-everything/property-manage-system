package edu.f4.security;

import com.alibaba.fastjson.JSON;
import edu.f4.result.JsonResult;
import edu.f4.enumEntity.ResultCode;
import edu.f4.result.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PeiYP
 * @since 2022年06月09日 11:47
 * 没有权限
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        JsonResult noPermission = ResultTool.fail(ResultCode.NO_PERMISSION);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        // 把Json数据放到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(noPermission));
    }
}
