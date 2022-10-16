package edu.f4.controller;

import cn.hutool.core.date.DateTime;
import edu.f4.pojo.Announcement;
import edu.f4.result.Result;
import edu.f4.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年10月13日 18:06
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    
    @Autowired
    private IAnnouncementService announcementService;

    @PostMapping
    public Result addAnnouncement(@RequestBody Announcement announcement) {

        announcement.setCreateTime(LocalDateTime.now().toString());
        return Result.ok(announcementService.save(announcement));
    }

    @DeleteMapping("/{id}")
    public Result delAnnouncement(@PathVariable Integer id) {
        return Result.ok(announcementService.removeById(id));
    }


    @GetMapping
    public Result queryAll() {

        List<Announcement> list = announcementService.list();
        List<Announcement> newList = new ArrayList<>();

        for (Announcement announcement : list) {
            String dateStr = announcement.getCreateTime().replace("T", " ");
            announcement.setCreateTime(dateStr);
            newList.add(announcement);
        }
        return Result.ok(newList);
    }
    
}
