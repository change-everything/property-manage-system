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
@TableName(value = "roleGroup")//角色组表
public class RoleGroup {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    //员工号
    private Integer empNum;
    //员工角色
    private  String empRole;
}
