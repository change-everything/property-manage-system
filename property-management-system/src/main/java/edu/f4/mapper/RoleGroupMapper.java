package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.f4.pojo.RoleGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleGroupMapper extends BaseMapper<RoleGroup> {

    RoleGroup getByEmpId(Integer empId);

    boolean grantAuthorityByRoleId(Integer roleId, Integer permId);


}
