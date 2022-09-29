package edu.f4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author PeiYP
 * @since 2022年09月29日 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemParam {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String chargeType;

    private String unit;

    private Double price;

}
