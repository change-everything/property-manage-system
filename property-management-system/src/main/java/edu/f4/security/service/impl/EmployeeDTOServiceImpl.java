package edu.f4.security.service.impl;

import cn.hutool.core.bean.BeanUtil;
import edu.f4.dto.EmployeeDTO;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.mapper.RoleGroupMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.pojo.Permissions;
import edu.f4.security.service.IEmployeeDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月08日 22:32
 */
@Service
public class EmployeeDTOServiceImpl implements IEmployeeDTOService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        EmployeeInfo emp = employeeInfoMapper.getEmpAndRoleAndPermsByEmpNum(Integer.parseInt(username));

        if (StringUtils.isEmpty(emp)) {
            throw new UsernameNotFoundException("找不到此用户");
        }

        System.out.println(emp);
        EmployeeDTO empDTO = BeanUtil.copyProperties(emp, EmployeeDTO.class);

        empDTO.setRoles(Collections.singletonList(emp.getRole()));

        List<Permissions> perms = emp.getPerms();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        perms.forEach(sysPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermCode());
            grantedAuthorities.add(grantedAuthority);
        });


        return new User(empDTO.getUsername(), passwordEncoder.encode(empDTO.getPassword()), true, true, true, true, grantedAuthorities);

    }
}
