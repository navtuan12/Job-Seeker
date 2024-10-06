package com.navtuan12.job_seeker_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = SecurityAutoConfiguration.class
@SpringBootApplication
public class JobSeekerServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobSeekerServerApplication.class, args);
	}
}