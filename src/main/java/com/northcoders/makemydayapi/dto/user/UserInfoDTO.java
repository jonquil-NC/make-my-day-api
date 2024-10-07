package com.northcoders.makemydayapi.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDTO {

    private String firstname;
    private String lastname;
    private String email;
}
