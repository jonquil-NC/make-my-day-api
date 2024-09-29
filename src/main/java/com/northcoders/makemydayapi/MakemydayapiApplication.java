package com.northcoders.makemydayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// https://www.tutorialspoint.com/spring_boot/spring_boot_scheduling.htm
@EnableScheduling
@SpringBootApplication
public class MakemydayapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakemydayapiApplication.class, args);
    }

}
