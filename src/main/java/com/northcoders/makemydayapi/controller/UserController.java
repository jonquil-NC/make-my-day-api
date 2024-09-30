package com.northcoders.makemydayapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/makemyday")
public class UserController {

    @PostMapping("/sign-in")
    ResponseEntity<String> signIn() {
        return null;
    }

    @PostMapping("/sign-up")
    ResponseEntity<String> signUp(){
        return null;
    }
}
