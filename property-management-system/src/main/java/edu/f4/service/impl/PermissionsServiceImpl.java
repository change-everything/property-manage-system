package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.PermissionsMapper;
import edu.f4.pojo.Permissions;
import edu.f4.result.TreeNode;
import edu.f4.service.IPermissionsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {


    // 将数据转换为树结构
    public List<TreeNode> queryAllFormTree() {

        /*
         * pid规则：
         *      员工操作：123
         *      部门操作：456
         *      角色操作：789
         *      缴费操作：258
         *      业主操作：369
         */

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(new TreeNode(123, "员工组", true, new ArrayList<>()));
        treeNodes.add(new TreeNode(456, "部门组", true, new ArrayList<>()));
        treeNodes.add(new TreeNode(789, "角色组", true, new ArrayList<>()));
        treeNodes.add(new TreeNode(258, "缴费组", true, new ArrayList<>()));
        treeNodes.add(new TreeNode(369, "业主组", true, new ArrayList<>()));

        List<Permissions> list = query().list();
        for (Permissions permissions : list) {

            if (permissions.getPermCode().matches(".*emp.*")) {
                treeNodes.get(0).getChildren().add(permissions);
            } else if (permissions.getPermCode().matches(".*dept.*")) {
                treeNodes.get(1).getChildren().add(permissions);
            } else if (permissions.getPermCode().matches(".*role.*")
                        || permissions.getPermCode().matches(".*perm.*")) {
                treeNodes.get(2).getChildren().add(permissions);
            } else if (permissions.getPermCode().matches(".*harge.*")) {
                treeNodes.get(3).getChildren().add(permissions);
            } else if (permissions.getPermCode().matches(".*owner.*")) {
                treeNodes.get(4).getChildren().add(permissions);
            }

        }


        return treeNodes;
    }

}
