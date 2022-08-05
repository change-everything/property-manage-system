package edu.f4.result;

import edu.f4.pojo.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月14日 23:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {


    private Integer permId;

    private String permName;

    private boolean disabled;


    private List<Permissions> children = null;

}
