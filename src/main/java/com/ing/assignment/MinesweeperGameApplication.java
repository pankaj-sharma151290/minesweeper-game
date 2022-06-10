package com.ing.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MinesweeperGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperGameApplication.class, args);
	}

}
