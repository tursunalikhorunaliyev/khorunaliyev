package com.khorunaliyev.khorunaliyev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TApplication {

	public static void main(String[] args) {
		SpringApplication.run(TApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer configureCORS(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/test/**")
						.allowedOrigins("http://127.0.0.1:5500")
						.allowedHeaders("Access-Control-Allow-Origin", "*")
						.allowedHeaders("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
						.allowedHeaders("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range")
						.maxAge(1728000);
			}
		};
	}

}
