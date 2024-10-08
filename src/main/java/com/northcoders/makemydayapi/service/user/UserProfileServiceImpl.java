package com.northcoders.makemydayapi.service.user;

import com.northcoders.makemydayapi.dto.user.UserProfileResponse;
import com.northcoders.makemydayapi.exception.UserNotFoundException;
import com.northcoders.makemydayapi.mapper.user.UserProfileMapper;
import com.northcoders.makemydayapi.model.User;
import com.northcoders.makemydayapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfileResponse getUserProfile(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        UserProfileResponse userProfileResponse = UserProfileMapper.toResponseDTO(existingUser);

        return userProfileResponse;
    }
}
