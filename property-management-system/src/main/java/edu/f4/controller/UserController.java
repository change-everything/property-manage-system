package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.f4.dto.Result;
import edu.f4.pojo.User;
import edu.f4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

/**
 * @author PeiYP
 * @since 2022年06月06日 19:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public Result login() {
        return Result.ok("这里是数据1");
    }

    @RequestMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        return Result.ok(user);
    }


    @RequestMapping("/{currentPage}/{pageSize}")
    public Result selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize, User user) {
        IPage<User> page = userService.getPage(currentPage, pageSize, user);
        // 如果当前页码值大于最大页码值，那么需要执行查询操作，使用最大页码值代替当前页码值
        if (currentPage > page.getPages()) {
            page = userService.getPage((int) page.getPages(), pageSize, user);
        }
        return Result.ok(page);
    }

}
