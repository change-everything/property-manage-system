package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.f4.pojo.CommunityInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author PeiYP
 * @since 2022年10月12日 22:42
 */
@Mapper
public interface CommunityMapper extends BaseMapper<CommunityInfo> {
}
