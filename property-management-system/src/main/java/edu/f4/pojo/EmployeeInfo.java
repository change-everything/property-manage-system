package edu.f4.pojo;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "employee_info")//员工信息表
public class EmployeeInfo {
    @TableId(type = IdType.AUTO)
    private Integer empId;
    //角色id
    private Integer roleId;
    //员工号
    private Integer empNum;
    //员工姓名
    private  String empName;
    //出生日期
    private String birthDate;
    //员工性别
    private Char gender;
    //员工住址
    private String address;
    //员工部门号
    private Integer empDepNum;
    //员工密码
    private String empPwd;
}
