package edu.f4.service.impl;

import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.mapper.RoleGroupMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IEmployeeDTOService;
import edu.f4.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author PeiYP
 * @since 2022年06月08日 22:32
 */
@Service
public class IEmployeeDTOServiceImpl implements IEmployeeDTOService {

    @Autowired
    private RoleGroupMapper roleGroupMapper;
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        EmployeeInfo emp = employeeInfoMapper.getEmpAndRoleAndPermsByEmpNum(Integer.parseInt(username));

        return null;
    }
}
