package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrmFaqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmFaqApplication.class, args);
    }
}

