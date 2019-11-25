package com.vferneda.minhaprevisaodotempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MinhaprevisaodotempoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MinhaprevisaodotempoApplication.class, args);
    }

}
