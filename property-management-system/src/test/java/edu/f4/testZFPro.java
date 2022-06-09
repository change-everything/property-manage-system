package edu.f4;

import edu.f4.mapper.ChargeInfoMapper;
import edu.f4.pojo.ChargeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testZFPro {

    @Autowired
    private ChargeInfoMapper chargeInfoMapper;

    @Test
    void test01() {
//        chargeInfoMapper.updateStatus(2);
    }
}
