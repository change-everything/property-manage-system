package edu.f4.mapper;


import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.f4.pojo.ChargeInfo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChargeInfoMapper extends BaseMapper<ChargeInfo> {

    List<ChargeInfo> queryChargeInfoByStatus(Integer status);

    // TODO: 2022/6/9  实现按id查询并修改字段
    boolean updateStatusById(Integer chaId);

    List<ChargeInfo> getChargeByRoomNum(Integer roomNum);

}
