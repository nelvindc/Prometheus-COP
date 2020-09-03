package com.ti.cop.opensource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

//@EnablePrometheusEndpoint
@SpringBootApplication
@ComponentScan("com.ti.cop.opensource")
public class TodoApplication {

	public static void main(String[] args)  {

		SpringApplication.run(TodoApplication.class, args);
		
	}
	

}