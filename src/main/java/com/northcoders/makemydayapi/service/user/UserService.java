package com.northcoders.makemydayapi.service.user;

import com.northcoders.makemydayapi.dto.user.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.user.UserInfoDTO;
import jakarta.validation.Valid;

public interface UserService {

    public UserInfoDTO signUp (@Valid AuthRequestDTO authRequestDTO);

}
