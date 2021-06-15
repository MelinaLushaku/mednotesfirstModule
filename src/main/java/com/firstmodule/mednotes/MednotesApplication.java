package com.firstmodule.mednotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MednotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MednotesApplication.class, args);
    }

}
