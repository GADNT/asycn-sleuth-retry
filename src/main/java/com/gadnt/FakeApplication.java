package com.gadnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@EnableRetry
public class FakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeApplication.class, args);
    }
}
