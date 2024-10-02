package com.northcoders.makemydayapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserInfoRequestDTO {

    private String firstname;
    private String lastname;
    private String email;
}
