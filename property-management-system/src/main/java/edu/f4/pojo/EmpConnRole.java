package edu.f4.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PeiYP
 * @since 2022年06月15日 23:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpConnRole {

    private Integer empRoleId;

    private Integer empId;

    private Integer roleId;

}
