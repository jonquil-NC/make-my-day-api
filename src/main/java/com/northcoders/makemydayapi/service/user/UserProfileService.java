package com.northcoders.makemydayapi.service.user;

import com.northcoders.makemydayapi.dto.user.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse getUserProfile(Long userId);
}
