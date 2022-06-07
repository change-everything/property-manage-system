package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.PermissionsMapper;
import edu.f4.pojo.Permissions;
import edu.f4.service.IPermissionsService;
import org.springframework.stereotype.Service;

@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions>implements IPermissionsService {
}
