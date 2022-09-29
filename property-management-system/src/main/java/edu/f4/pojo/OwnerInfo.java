package edu.f4.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "owner_info")//业主信息
public class OwnerInfo {
    @TableId(type = IdType.AUTO)
    private Integer ownerId;
    //业主编号
    private Integer ownerNum;
    //业主姓名
    private String ownerName;
    //房号
    private Integer roomNum;
    //房屋面积
    private String floorArea;
    //工作单位
    private  String workUnit;
    //联系电话
    private String phoneNumber;


}
