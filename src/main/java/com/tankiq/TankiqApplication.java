package com.tankiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TankiqApplication {
    public static void main(String[] args) {
        SpringApplication.run(TankiqApplication.class, args);
    }
}
