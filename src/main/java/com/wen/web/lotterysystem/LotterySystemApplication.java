package com.wen.web.lotterysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
public class LotterySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotterySystemApplication.class, args);
	}


}
