package edu.f4.pojo;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author PeiYP
 * @since 2022年10月13日 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String context;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

}
