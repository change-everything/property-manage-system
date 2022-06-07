package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.RoleGroupMapper;
import edu.f4.pojo.RoleGroup;
import edu.f4.service.IRoleGroupService;
import org.springframework.stereotype.Service;

@Service
public class RoleGroupServiceImpl extends ServiceImpl<RoleGroupMapper, RoleGroup>implements IRoleGroupService {
}
