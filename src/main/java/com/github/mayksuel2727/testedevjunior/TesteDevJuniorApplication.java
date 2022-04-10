package com.github.mayksuel2727.testedevjunior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class TesteDevJuniorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteDevJuniorApplication.class, args);
    }

}
