package edu.f4.dto;

import edu.f4.pojo.Permissions;
import lombok.Data;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月09日 22:35
 */
@Data
public class EmpDTO {

    private Integer empId;
    private Integer empNum;
    private String empPwd;
    private String token;
    private String role;
    private List<String> permCode;

}
