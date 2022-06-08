package edu.f4;

import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author PeiYP
 * @since 2022年06月08日 17:01
 */
@SpringBootTest
public class TestMapper {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Test
    void testAll() {
        EmployeeInfo emp = employeeInfoMapper.getEmpAndRoleAndPermsByEmpNum(19501);

        System.out.println(emp);
    }
}
