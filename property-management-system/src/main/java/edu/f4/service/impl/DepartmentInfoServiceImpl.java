package edu.f4.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.dto.DeptAndEmpVo;
import edu.f4.dto.EmpAndDeptDTO;
import edu.f4.mapper.DepartmentInfoMapper;
import edu.f4.pojo.DepartmentInfo;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.pojo.RoleGroup;
import edu.f4.service.IDepartmentInfoService;
import edu.f4.service.IEmployeeInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentInfoServiceImpl extends ServiceImpl<DepartmentInfoMapper, DepartmentInfo> implements IDepartmentInfoService {

    @Autowired
    private IEmployeeInfoService employeeInfoService;


    @Transactional
    @Override
    public boolean deleteDeptById(Integer deptId) {

        DepartmentInfo departmentInfo = query().eq("dep_id", deptId).one();

        if (departmentInfo == null) {
            return false;
        }

        removeById(deptId);


        QueryWrapper<EmployeeInfo> wrapper = new QueryWrapper<>();

        wrapper.eq("emp_dep_num", departmentInfo.getDepNum());

        List<EmployeeInfo> list = employeeInfoService.list(wrapper);

        for (EmployeeInfo employeeInfo : list) {
            employeeInfo.setEmpDepNum(900);
        }

        return employeeInfoService.updateBatchById(list);

    }

    @Override
    public List<DeptAndEmpVo> findEmpByDeptNum() {

        List<DepartmentInfo> departmentInfos = list();
        List<DeptAndEmpVo> deptList = new ArrayList<>();


        for (DepartmentInfo departmentInfo : departmentInfos) {
            DeptAndEmpVo deptAndEmpVo = BeanUtil.copyProperties(departmentInfo, DeptAndEmpVo.class);
            List<EmployeeInfo> empByDeptNum = employeeInfoService.findEmpByDeptNum(deptAndEmpVo.getDepNum());
            List<EmpAndDeptDTO> empList = new ArrayList<>();
            for (EmployeeInfo employeeInfo : empByDeptNum) {
                EmpAndDeptDTO empAndDeptDTO = BeanUtil.copyProperties(employeeInfo, EmpAndDeptDTO.class);
                RoleGroup empRole = employeeInfoService.getEmpRole(empAndDeptDTO.getEmpId());
                empAndDeptDTO.setRoleDest(empRole.getRoleDest());
                empList.add(empAndDeptDTO);
            }

            deptAndEmpVo.setEmp(empList);
            deptList.add(deptAndEmpVo);
        }

        return deptList;

    }


}
