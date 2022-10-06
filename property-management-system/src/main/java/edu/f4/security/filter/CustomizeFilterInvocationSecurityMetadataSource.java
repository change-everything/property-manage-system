package edu.f4.security.filter;

import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.mapper.PermissionsMapper;
import edu.f4.pojo.Permissions;
import edu.f4.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月09日 16:41
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        // 获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println(requestUrl);
        String requestMethod = UrlUtils.subUrl(o.toString());
        requestUrl = UrlUtils.splitUrl(requestUrl);
        String url = requestMethod + requestUrl;
        System.out.println(url);

        // 查询具体某个接口的权限
        List<Permissions> permissionList = permissionsMapper.selectListByPath(url);
        if (permissionList == null || permissionList.size() == 0) {
            // 请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }
        String[] attributes = new String[permissionList.size()];
        for (int i = 0; i < permissionList.size(); i++) {
            attributes[i] = permissionList.get(i).getPermCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
