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
@TableName(value = "department_info")//部门信息表
public class DepartmentInfo {
    @TableId(type = IdType.AUTO)
    private Integer depId;
    //部门号
    private Integer depNum;
    //部门名称
    private String depName;
    //部门负责人
    private String depHead;
    //部门电话
    private String depTelNum;
}
