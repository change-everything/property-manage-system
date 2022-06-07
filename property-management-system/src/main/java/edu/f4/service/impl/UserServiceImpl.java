package edu.f4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.Result;
import edu.f4.mapper.UserMapper;
import edu.f4.pojo.User;
import edu.f4.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

/**
 * @author PeiYP
 * @since 2022年06月07日 8:46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public IPage<User> getPage(int currentPage, int pageSize, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(user.getAddress()), User::getAddress, user.getAddress());
        queryWrapper.like(Strings.isNotEmpty(user.getName()), User::getName, user.getName());

        IPage<User> page = new Page<>(currentPage, pageSize);
        userMapper.selectPage(page, queryWrapper);
        return page;
    }


}
