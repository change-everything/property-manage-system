package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.f4.pojo.RoleGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleGroupMapper extends BaseMapper<RoleGroup> {

    RoleGroup getByEmpId(Integer empId);

    boolean grantAuthorityByRoleId(Integer roleId, Integer permId);


    List<Integer> getPermIds(Integer roleId);


}
