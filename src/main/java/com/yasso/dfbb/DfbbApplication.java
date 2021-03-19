package com.yasso.dfbb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages = {"com.yasso.dfbb.mapper"})
@SpringBootApplication
public class DfbbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DfbbApplication.class, args);
    }

}
