package edu.f4;

import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.mapper.RoleGroupMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.pojo.RoleGroup;
import edu.f4.service.IEmployeeInfoService;
import edu.f4.service.IRoleGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月08日 17:01
 */
@SpringBootTest
public class TestMapper {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    @Autowired
    private RoleGroupMapper roleGroupMapper;

    @Autowired
    private IRoleGroupService roleGroupService;


    @Autowired
    private IEmployeeInfoService employeeInfoService;

    @Test
    void testAll() {
        EmployeeInfo emp = employeeInfoMapper.getEmpAndRoleAndPermsByEmpNum(19501);

        System.out.println(emp);
    }

    @Test
    void test01() {
        EmployeeInfo employeeInfo = employeeInfoMapper.queryByEmpNum(19501);

        System.out.println(employeeInfo);
    }

    @Test
    void test02() {
        RoleGroup byEmpId = roleGroupMapper.getByEmpId(1);
        System.out.println(byEmpId);
    }


    @Test
    void test03() {

        String str = "2022-06-12T16:00:00.000Z";

        String[] ts = str.split("T");

        System.out.println(ts[0]);

    }


    @Test
    void test04() {

        //EmployeeInfo empAndDept = employeeInfoService.getEmpAndDept(5);

        //System.out.println(empAndDept);

    }

    @Test
    void test05() {

        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String format = time.format(date);

        System.out.println(format);

    }

    @Test
    void test06() {

        List<Integer> permIds = roleGroupService.getPermIds(5);

        System.out.println(permIds);

    }
}
