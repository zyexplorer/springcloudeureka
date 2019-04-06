package com.pab.springcloudeurekaclientprovider1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudEurekaClientProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientProvider1Application.class, args);
	}

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hi")
	public String test(@RequestParam("name") String name){
		return "Hi " + name + " , I am from provider1:" + port;
	}
}
