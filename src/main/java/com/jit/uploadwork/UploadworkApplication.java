package com.jit.uploadwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jit.uploadwork.mapper")
public class UploadworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadworkApplication.class, args);
	}
}
