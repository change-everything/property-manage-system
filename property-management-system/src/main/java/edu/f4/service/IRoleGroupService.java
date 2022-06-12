package edu.f4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.RoleGroup;

import java.util.List;

public interface IRoleGroupService extends IService<RoleGroup> {
    boolean grantAuthorityByRoleId(Integer roleId, List<Integer> permIds);

}
