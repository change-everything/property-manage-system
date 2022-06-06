package edu.f4.controller;

import edu.f4.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PeiYP
 * @since 2022年06月06日 19:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public Result login() {
        return Result.ok("这里是数据");
    }


}
