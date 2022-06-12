package edu.f4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.f4.pojo.Permissions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<Permissions> selectListByPath(String url);

}
