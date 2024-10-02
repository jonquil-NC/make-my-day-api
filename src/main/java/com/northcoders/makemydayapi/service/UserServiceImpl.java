package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import com.northcoders.makemydayapi.exception.UserAlreadyExistsException;
import com.northcoders.makemydayapi.model.User;
import com.northcoders.makemydayapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDTO signUp(AuthRequestDTO authRequestDTO) {

        boolean userExists = userRepository
                .findByEmail(authRequestDTO.getEmail())
                .isPresent();
        if (userExists){
            throw new UserAlreadyExistsException("User with email " + authRequestDTO.getEmail() + " already exists!");
        }

        String encryptedPassword = passwordEncoder.encode(authRequestDTO.getPassword());

        User user = User.builder()
                .firstname(authRequestDTO.getFirstname())
                .lastname(authRequestDTO.getLastname())
                .email(authRequestDTO.getEmail())
                .password(encryptedPassword)
                .build();

        userRepository.save(user);

        return new UserInfoDTO(user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
