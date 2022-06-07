package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.f4.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author PeiYP
 * @date 2022年06月07日 8:43
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {



}
