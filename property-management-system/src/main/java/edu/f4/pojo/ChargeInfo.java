package edu.f4.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "charge_info")//收费信息表
public class ChargeInfo {
    @TableId(type = IdType.AUTO)
    private Integer chaId;
    //房号
    private Integer roomNum;
    //业主编号
    private Integer ownerNum;
    //收费日期
    private String chaDate;
    //收费类型
    private String chaType;
    //收费数量
    private Integer chaQuantity;
    //收费金额
    private Double chaSum;
    //员工号
    private Integer empNum;

    @TableField(select = false)
    private OwnerInfo ownerInfo;

    @TableField(select = false)
    private List<ChargeInfo> children;
}
