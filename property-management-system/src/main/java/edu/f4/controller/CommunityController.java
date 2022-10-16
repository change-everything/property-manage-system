package edu.f4.controller;

import edu.f4.result.Result;
import edu.f4.service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author PeiYP
 * @since 2022年10月12日 22:56
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private ICommunityService communityService;

    @GetMapping("/info")
    public Result getInfo() {
        Map<String, Object> map = communityService.getCommunityInfo();
        return Result.ok(map);
    }


}
