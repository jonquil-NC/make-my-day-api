//package com.northcoders.makemydayapi.controller;
//
////import com.northcoders.makemydayapi.service.PopulateDatabaseScheduler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/utils")
//public class UtilController {
//
//    @Autowired
//    private PopulateDatabaseScheduler populateDatabaseScheduler;
//
//
//    @GetMapping("/skiddle/populatedb")
//    public String triggerPopulateDatabase(){
//        this.populateDatabaseScheduler.populateDatabaseForSkiddle();
//        return "Populated Database";
//    }
//}
