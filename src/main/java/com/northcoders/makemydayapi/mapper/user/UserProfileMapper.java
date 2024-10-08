package com.northcoders.makemydayapi.mapper.user;

import com.northcoders.makemydayapi.dto.user.UserProfileResponse;
import com.northcoders.makemydayapi.model.User;

public class UserProfileMapper {
    public static final UserProfileResponse toResponseDTO(User user) {

        return UserProfileResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .itinerary(user.getItinerary())
                .build();
    }
}
