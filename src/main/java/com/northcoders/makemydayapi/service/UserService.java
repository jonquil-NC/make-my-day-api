package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import jakarta.validation.Valid;

public interface UserService {

    public UserInfoDTO signUp (@Valid AuthRequestDTO authRequestDTO);

}
