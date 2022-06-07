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
@TableName(value = "permissions")//权限表
public class Permissions {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //角色id
    private Integer roleId;
    //角色权限
    private  String rolePer;
}
