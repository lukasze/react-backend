package com.example.reactbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ReactBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ReactBackendApplication.class, args);

        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);

    }
}