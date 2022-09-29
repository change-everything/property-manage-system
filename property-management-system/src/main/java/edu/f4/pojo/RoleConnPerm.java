package edu.f4.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PeiYP
 * @since 2022年06月10日 22:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleConnPerm {

    private Integer roleConnPerm;
    private Integer roleId;
    private Integer permId;

}
