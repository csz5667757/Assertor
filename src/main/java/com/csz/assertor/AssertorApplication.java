package com.csz.assertor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.csz.assertor.sys.mapper")
public class AssertorApplication extends SpringBootServletInitializer {

	public AssertorApplication(){
		this.setRegisterErrorPageFilter(false);
	}

	public static void main(String[] args) {
		SpringApplication.run(AssertorApplication.class, args);
	}
}

