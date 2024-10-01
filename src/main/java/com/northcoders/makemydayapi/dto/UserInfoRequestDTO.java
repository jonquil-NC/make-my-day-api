package com.northcoders.makemydayapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoRequestDTO {

    private String firstname;
    private String lastname;
    private String email;
}
