package edu.f4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.CommunityMapper;
import edu.f4.pojo.CommunityInfo;
import edu.f4.service.ICommunityService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PeiYP
 * @since 2022年10月12日 22:44
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, CommunityInfo> implements ICommunityService {
    @Override
    public Map<String, Object> getCommunityInfo() {
        HashMap<String, Object> map = new HashMap<>();
        List<CommunityInfo> list = list();
        // 空置数
        int vacanciesCount = 0;
        // 入住数
        int occupancyCount = 0;
        // 出租数
        int rentalsCount = 0;
        int total = list.size();
        for (CommunityInfo communityInfo : list) {
            if (communityInfo.getStatus() == 1) {
                occupancyCount++;
            } else if (communityInfo.getStatus() == 2) {
                rentalsCount++;
            } else if (communityInfo.getStatus() == 0) {
                vacanciesCount++;
            } else {
                throw new RuntimeException("状态错误");
            }
        }

        map.put("occupancyRate", occupancyCount);
        map.put("rentalRate", rentalsCount);
        map.put("vacancyRate", vacanciesCount);
        map.put("total", total);
        return map;
    }
}
