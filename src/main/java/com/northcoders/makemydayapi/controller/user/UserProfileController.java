package com.northcoders.makemydayapi.controller.user;

import com.northcoders.makemydayapi.dto.user.UserProfileResponse;
import com.northcoders.makemydayapi.service.user.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable("id") Long id) {
        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(id);

        return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }
}
