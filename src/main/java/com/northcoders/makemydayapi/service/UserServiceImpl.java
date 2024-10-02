package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import com.northcoders.makemydayapi.exception.UserAlreadyExistsException;
import com.northcoders.makemydayapi.model.User;
import com.northcoders.makemydayapi.repository.UserRepository;
import com.northcoders.makemydayapi.validator.AuthRequestValidator;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthRequestValidator authRequestValidator;

    @Override
    public UserInfoDTO signUp(@Valid AuthRequestDTO authRequestDTO) {


        Errors errors = new BeanPropertyBindingResult(authRequestDTO, "request");
        authRequestValidator.validate(authRequestDTO, errors);

        if (errors.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            errors.getAllErrors().forEach(error -> {

                errorMessage.append(error.getDefaultMessage()).append("; ");
            });

            throw new IllegalArgumentException("Validation failed: " + errorMessage);
        }
        boolean userExists = userRepository
                .findByEmail(authRequestDTO.getEmail())
                .isPresent();

        if (userExists){
            throw new UserAlreadyExistsException("User with email " + authRequestDTO.getEmail() + " already exists!");
        }

        String encryptedPassword = passwordEncoder.encode(authRequestDTO.getPassword());

//        User user = User.builder()
//                .firstname(authRequestDTO.getFirstname())
//                .lastname(authRequestDTO.getLastname())
//                .email(authRequestDTO.getEmail())
//                .password(encryptedPassword)
//                .build();

        User user = new User();
        user.setFirstname(authRequestDTO.getFirstname());
        user.setLastname(authRequestDTO.getLastname());
        user.setEmail(authRequestDTO.getEmail());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        System.out.println("User: " + user.getFirstname());
        return new UserInfoDTO(user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
