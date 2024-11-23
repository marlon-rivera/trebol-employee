package com.trebol.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class UsersTrebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersTrebolApplication.class, args);
	}

}
