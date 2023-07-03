package com.deercorp.blackcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlackcompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlackcompanyApplication.class, args);
    }
}
