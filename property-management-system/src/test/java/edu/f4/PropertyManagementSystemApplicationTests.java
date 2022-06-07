package edu.f4;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.pojo.User;
import edu.f4.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyManagementSystemApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void testFirst() {
        User byId = userService.getById(1);

        System.out.println(byId);
    }

    //@Test
    //void testGetPage() {
    //    IPage<User> page = userService.getPage(1, 3, null);
    //
    //    System.out.println(page.getPages());
    //    System.out.println(page.getTotal());
    //}
}
