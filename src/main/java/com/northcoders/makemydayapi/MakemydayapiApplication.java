package com.northcoders.makemydayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.DateTimeException;
import java.time.LocalDate;

@SpringBootApplication
public class MakemydayapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakemydayapiApplication.class, args);
    }

}
