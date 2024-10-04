package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.model.User;

public class AuthRequestMapper {

    public static User toUser (AuthRequestDTO request) {

        User user = new User();

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

}
