package edu.f4.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.f4.mapper.DepartmentInfoMapper;
import edu.f4.pojo.DepartmentInfo;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.service.IDepartmentInfoService;
import edu.f4.service.IEmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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


}
