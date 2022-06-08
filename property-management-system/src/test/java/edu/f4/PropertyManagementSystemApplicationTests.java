package edu.f4;

import edu.f4.pojo.OwnerInfo;
import edu.f4.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PropertyManagementSystemApplicationTests {

    @Autowired
    private IOwnerInfoService ownerInfoService;
    @Autowired
    private IChargeInfoService chargeInfoService;
    @Autowired
    private IDepartmentInfoService departmentInfoService;
    @Autowired
    private IEmployeeInfoService employeeInfoService;
    @Autowired
    private IRoleGroupService roleGroupService;



    @Test
    void testOwner() {
        List<OwnerInfo> list = ownerInfoService.list();

        list.forEach(System.out::println);
    }

    @Test
    void testCharge() {
        System.out.println(chargeInfoService.list());
    }


    @Test
    void testDept() {
        System.out.println(departmentInfoService.list());
    }


    @Test
    void testEmp() {
        System.out.println(employeeInfoService.list());
    }

    @Test
    void testRole() {

        System.out.println(roleGroupService.list());
    }
}
