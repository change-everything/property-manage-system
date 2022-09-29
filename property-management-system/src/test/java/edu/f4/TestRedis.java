package edu.f4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author PeiYP
 * @since 2022年06月16日 9:02
 */
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void test01() {

        String num = redisTemplate.opsForValue().get("num");

        System.out.println(num);

    }
}
