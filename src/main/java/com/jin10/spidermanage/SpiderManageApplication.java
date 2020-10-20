package com.jin10.spidermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.jin10.spidermanage.mapper")
public class SpiderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderManageApplication.class, args);
    }

}
