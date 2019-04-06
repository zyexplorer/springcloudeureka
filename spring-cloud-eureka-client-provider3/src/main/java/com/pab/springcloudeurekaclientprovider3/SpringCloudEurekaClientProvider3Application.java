package com.pab.springcloudeurekaclientprovider3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaClientProvider3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientProvider3Application.class, args);
	}
}
