package com.jin10.spidermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@MapperScan("com.jin10.spidermanage.mapper")
@EnableAsync
public class SpiderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderManageApplication.class, args);
    }

}
