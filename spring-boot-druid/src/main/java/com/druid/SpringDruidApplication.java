package com.druid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务，然后在访问数据库的Service方法上添加注解 @Transactional
public class SpringDruidApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDruidApplication.class, args);
    }
}
