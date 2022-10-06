package edu.f4.dto;

import lombok.Data;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月09日 22:35
 */
@Data
public class EmpLoginDTO {

    private Integer empId;
    private Integer empNum;
    private String empName;
    //private String empPwd;
    private String token;
    private String role;
    private List<String> permCode;

}
