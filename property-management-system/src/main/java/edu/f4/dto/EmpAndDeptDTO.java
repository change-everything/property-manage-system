package edu.f4.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author PeiYP
 * @since 2022年06月12日 7:55
 */
@Data
public class EmpAndDeptDTO {

    private Integer empId;
    // 员工号
    private Integer empNum;
    // 员工姓名
    private String empName;
    // 出生日期
    private String birthDate;
    // 员工性别
    private String gender;
    // 员工住址
    private String address;
    // 员工部门号
    private String empDepName;
    // 员工角色
    private String roleDest;
    // 员工电话
    private String phone;
    // 员工密码
    private String empPwd;

}
