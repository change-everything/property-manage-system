package edu.f4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.CommunityInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author PeiYP
 * @since 2022年10月12日 22:43
 */
public interface ICommunityService extends IService<CommunityInfo> {

    /**
     * 返回小区信息
     */
    Map<String, Object> getCommunityInfo();
}
