package com.navtuan12.job_seeker_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.navtuan12.job_seeker_server.repository.UserRepository;

@SpringBootApplication
public class JobSeekerServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobSeekerServerApplication.class, args);
	}
}
