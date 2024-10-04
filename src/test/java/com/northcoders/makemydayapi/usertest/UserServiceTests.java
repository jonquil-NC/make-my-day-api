package com.northcoders.makemydayapi.usertest;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import com.northcoders.makemydayapi.exception.UserAlreadyExistsException;
import com.northcoders.makemydayapi.mapper.AuthRequestMapper;
import com.northcoders.makemydayapi.model.User;
import com.northcoders.makemydayapi.repository.UserRepository;
import com.northcoders.makemydayapi.service.UserServiceImpl;
import com.northcoders.makemydayapi.validator.AuthRequestValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserServiceTests {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private AuthRequestValidator authRequestValidator;

    @Test
    public void signUp_whenUserDoesNotExist_shouldCreateUserAndReturnUserInfoDTO() {

        var request = new AuthRequestDTO("John", "Doe", "john_doe@gmail.com", "AcsC508pS8$8r5H14Ii");

        var expectedResult = new UserInfoDTO("John", "Doe", "john_doe@gmail.com");

        var user = AuthRequestMapper.toUser(request);

        var actualResult = userServiceImpl.signUp(request);

        when(mockUserRepository.findByEmail(request.getEmail())).thenReturn(null);
        when(mockUserRepository.save(user)).thenReturn(user);

        verify(mockUserRepository, times(1)).findByEmail(request.getEmail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);


    }

    @Test
    public void signUp_whenUserAlreadyExists_shouldThrowUserAlreadyExistsException() {

        var request = new AuthRequestDTO("John", "Doe", "john_doe@gmail.com", "AcsC508pS8$8r5H14Ii");

        var existingUser = new User(1L, "Murphy", "Denny", "john_doe@gmail.com", "AcsC508pS8$8r5H14Ii");

        when(mockUserRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));

        Exception e = assertThrows(UserAlreadyExistsException.class, () -> {
            userServiceImpl.signUp(request);
        });

        String expectedMessage = "User with email " + request.getEmail() + " already exists!";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contentEquals(expectedMessage));

        verify(mockUserRepository, never()).save(any(User.class));
    }

}
