package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.RoleGroupMapper;
import edu.f4.pojo.RoleGroup;
import edu.f4.service.IRoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleGroupServiceImpl extends ServiceImpl<RoleGroupMapper, RoleGroup>implements IRoleGroupService {

    @Autowired
    private RoleGroupMapper roleGroupMapper;

    @Override
    public boolean grantAuthorityByRoleId(Integer roleId, List<Integer> permIds) {

        try {
            for (Integer permId : permIds) {
                roleGroupMapper.grantAuthorityByRoleId(roleId, permId);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }



}
