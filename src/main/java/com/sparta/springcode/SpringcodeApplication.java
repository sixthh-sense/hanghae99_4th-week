package com.sparta.springcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcodeApplication.class, args);
    }

}
