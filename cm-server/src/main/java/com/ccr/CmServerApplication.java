package com.ccr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 31373
 */
@SpringBootApplication
@EnableTransactionManagement // 开启事务
public class CmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmServerApplication.class, args);
    }

}
