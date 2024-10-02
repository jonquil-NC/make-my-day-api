package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import com.northcoders.makemydayapi.service.UserService;
import com.northcoders.makemydayapi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/makemyday")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/sign-in")
    ResponseEntity<String> signIn() {
        return null;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserInfoDTO> signUp(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        UserInfoDTO userInfo = userService.signUp(authRequestDTO);
        System.out.println("Response: " + userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
