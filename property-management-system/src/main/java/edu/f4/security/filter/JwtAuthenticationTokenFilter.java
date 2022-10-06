package edu.f4.security.filter;

import edu.f4.security.service.impl.EmployeeDTOServiceImpl;
import edu.f4.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PeiYP
 * @since 2022年06月09日 13:36
 * 用来判断JWT是否有效
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * 直接将我们前面写好的service注入进来，通过service获取到当前用户的权限
     * */
    @Autowired
    private EmployeeDTOServiceImpl employeeDTOService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取当请求头中的token，其实这里多余，完全可以使用HttpServletRequest来获取
        String authToken = httpServletRequest.getHeader("UserToken");

        // 获取到当前用户的account
        String account = JwtUtil.getMemberAccountByJwtToken(httpServletRequest);

        System.out.println("自定义JWT过滤器获得用户名为"+account);

        // 当token中的username不为空时进行验证token是否是有效的token
        if (!account.equals("")) {
            // token中username不为空，并且Context中的认证为空，进行token验证

            // 获取到用户的信息，也就是获取到用户的权限
            UserDetails userDetails = this.employeeDTOService.loadUserByUsername(account);

            if (JwtUtil.checkToken(authToken)) {   // 验证当前token是否有效

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                //将authentication放入SecurityContextHolder中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 放行给下个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
