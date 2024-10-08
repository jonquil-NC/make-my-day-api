package com.northcoders.makemydayapi.dto.user;

import com.northcoders.makemydayapi.model.activity.Activity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserProfileResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    private List<Activity> itinerary;
}
