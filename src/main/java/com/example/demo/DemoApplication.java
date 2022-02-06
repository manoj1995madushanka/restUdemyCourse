package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// this will scan classes of provided package as a parameter
//@ComponentScan({"com.example.controller","com.example.service"})
@EntityScan("com.example.demo.entity")
@EnableJpaRepositories("com.example.demo.repository")
@EnableSwagger2 // swagger configuration, for access swagger doc go to localhost:8080/swagger-ui.html
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
