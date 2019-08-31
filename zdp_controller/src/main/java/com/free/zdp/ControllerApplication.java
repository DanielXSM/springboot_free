package com.free.zdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com"})
@EntityScan("com.free.zdp.domain")
@EnableJpaRepositories("com.free.zdp.repository")

public class ControllerApplication
{

	
	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);
	}
}
