package com.northcoders.makemydayapi.usertest;

import com.northcoders.makemydayapi.dto.AuthRequestDTO;
import com.northcoders.makemydayapi.dto.UserInfoDTO;
import com.northcoders.makemydayapi.mapper.AuthRequestMapper;
import com.northcoders.makemydayapi.repository.UserRepository;
import com.northcoders.makemydayapi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserServiceTests {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void signUp_whenUserDoesNotExist_shouldCreateUserAndReturnUserInfoDTO() {

        var request = new AuthRequestDTO("John", "Doe", "john_doe@gmail.com", "password");

        var expectedResult = new UserInfoDTO("John", "Doe", "john_doe@gmail.com");

        var user = AuthRequestMapper.toUser(request);

        var actualResult = userServiceImpl.signUp(request);

        when(mockUserRepository.findByEmail(request.getEmail())).thenReturn(null);
        when(mockUserRepository.save(user)).thenReturn(user);

        verify(mockUserRepository, times(1)).findByEmail(request.getEmail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);


    }

}
