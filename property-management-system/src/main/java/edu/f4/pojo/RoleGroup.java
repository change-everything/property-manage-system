package edu.f4.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role_group")//角色组表
public class RoleGroup implements GrantedAuthority {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    //员工角色
    private String roleName;

    // 角色描述
    private String roleDest;


    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
