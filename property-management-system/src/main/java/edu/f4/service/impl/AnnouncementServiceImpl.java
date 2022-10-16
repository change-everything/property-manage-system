package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.AnnouncementMapper;
import edu.f4.pojo.Announcement;
import edu.f4.service.IAnnouncementService;
import org.springframework.stereotype.Service;

/**
 * @author PeiYP
 * @since 2022年10月13日 15:04
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
}
