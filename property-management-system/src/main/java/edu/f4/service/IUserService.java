package edu.f4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.dto.Result;
import edu.f4.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.redis.connection.ReactiveSubscription;

import java.awt.print.Book;

/**
 * @author PeiYP
 * @date 2022年06月07日 8:46
 */
public interface IUserService extends IService<User> {

    IPage<User> getPage(int currentPage, int pageSize, User user);

}
