package com.northcoders.makemydayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DateTimeException;
import java.time.LocalDate;

@SpringBootApplication
public class MakemydayapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakemydayapiApplication.class, args);

        String dataParsing = "2024-10-16";

        try {
            LocalDate parsedDate = LocalDate.parse(dataParsing);
            System.out.println("parsed date is " + parsedDate);
        } catch (DateTimeException e) {
            System.err.println("Error parsing data" + e.getMessage());
        }


    }

}
