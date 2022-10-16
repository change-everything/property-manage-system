package edu.f4.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月12日 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "no_pay_charge_info")
public class NoPayChargeInfo {

    @TableId(value = "cha_id")
    private Integer chaId;
    private Integer roomNum;
    private Integer ownerNum;
    private String chaType;
    private Integer chaQuantity;
    private Double chaSum;

    @TableField(select = false)
    private List<NoPayChargeInfo> children;

}
