package com.firstmodule.mednotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableEurekaClient
public class MednotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MednotesApplication.class, args);
    }

}
