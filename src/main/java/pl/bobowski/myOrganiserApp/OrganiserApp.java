package pl.bobowski.myOrganiserApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrganiserApp {

	public static void main(String[] args) {
		SpringApplication.run(OrganiserApp.class, args);
	}
}
