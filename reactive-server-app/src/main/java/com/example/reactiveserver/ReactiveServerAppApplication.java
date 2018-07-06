package com.example.reactiveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@SpringBootApplication
public class ReactiveServerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServerAppApplication.class, args);
    }

    @Bean
    public IdGenerator idGenerator() {
        return new JdkIdGenerator();
    }
}
