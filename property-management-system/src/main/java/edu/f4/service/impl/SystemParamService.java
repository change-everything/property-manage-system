package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.SystemParamMapper;
import edu.f4.pojo.SystemParam;
import edu.f4.service.ISystemParamService;
import org.springframework.stereotype.Service;

/**
 * @author PeiYP
 * @since 2022年09月29日 21:10
 */
@Service
public class SystemParamService extends ServiceImpl<SystemParamMapper, SystemParam> implements ISystemParamService {
}
