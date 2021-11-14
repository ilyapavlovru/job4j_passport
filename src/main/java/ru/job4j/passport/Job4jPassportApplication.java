package ru.job4j.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Job4jPassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jPassportApplication.class, args);
    }

}
