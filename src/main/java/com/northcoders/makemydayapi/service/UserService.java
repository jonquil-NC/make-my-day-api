package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;

public interface UserService {

    public UserInfoDTO signUp (AuthRequestDTO authRequestDTO);
}
