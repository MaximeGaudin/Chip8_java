package com.mgaudin.chip8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Chip8Application {

    public static void main(String[] args) {
        SpringApplication.run(Chip8Application.class, args);
    }
}
