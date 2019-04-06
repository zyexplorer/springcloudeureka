package com.pab.springcloudeurekaclientprovider4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
public class SpringCloudEurekaClientProvider4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientProvider4Application.class, args);
	}
}
