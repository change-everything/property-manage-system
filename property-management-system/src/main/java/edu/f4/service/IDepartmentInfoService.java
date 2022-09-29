package edu.f4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.f4.dto.DeptAndEmpVo;
import edu.f4.pojo.DepartmentInfo;

import java.util.List;
import java.util.Map;

public interface IDepartmentInfoService extends IService<DepartmentInfo> {
    boolean deleteDeptById(Integer deptNum);

    List<DeptAndEmpVo> findEmpByDeptNum();
}
