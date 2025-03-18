package edu.innotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LimitApp {
    public static void main(String[] args) {
        SpringApplication.run(LimitApp.class, args);
    }
}