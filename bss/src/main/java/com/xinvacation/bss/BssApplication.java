package com.xinvacation.bss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = "com.xinvacation")
@EnableJpaAuditing
public class BssApplication {

    public static void main(String[] args) {
        SpringApplication.run(BssApplication.class, args);
    }

}

