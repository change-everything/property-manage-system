package edu.f4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.pojo.Permissions;
import edu.f4.result.TreeNode;

import java.util.List;

public interface IPermissionsService extends IService<Permissions> {

    List<TreeNode> queryAllFormTree();

}
