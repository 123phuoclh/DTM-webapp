package org.example.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringApplicationConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories("org.example.Backend.repository")
@ComponentScan(basePackages = {"org.example.Backend"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}