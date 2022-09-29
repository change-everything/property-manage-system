package edu.f4.dto;

import edu.f4.pojo.EmployeeInfo;
import lombok.Data;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年09月28日 23:02
 */
@Data
public class DeptAndEmpVo {
    private Integer depId;
    //部门号
    private Integer depNum;
    //部门名称
    private String depName;
    //部门负责人
    private String depHead;
    //部门电话
    private String depTelNum;

    private List<EmployeeInfo> emp;
}
