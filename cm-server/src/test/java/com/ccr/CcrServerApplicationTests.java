package com.ccr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class CcrServerApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void testPassword() {
    	String password = "123456";
    	//加密密码
        String encodedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(encodedPassword);
    }
}
