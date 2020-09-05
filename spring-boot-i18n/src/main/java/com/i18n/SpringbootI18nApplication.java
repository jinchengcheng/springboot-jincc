package com.i18n;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务，然后在访问数据库的Service方法上添加注解 @Transactional
@MapperScan("com.**.dao") //扫描mapper
public class SpringbootI18nApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootI18nApplication.class, args);
	}
}
