package edu.f4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PeiYP
 * @since 2022年10月12日 22:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "community_info")//收费信息表
public class CommunityInfo {

    @TableId(type = IdType.AUTO)
    private Integer comId;

    private Integer status;

    private Integer unit;

    private Integer ownerId;

    private Integer roomNum;

}
