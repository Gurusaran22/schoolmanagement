package com.guru.depend.servicetest;

import com.guru.depend.entity.User;
import com.guru.depend.repository.UserRepository;
import com.guru.depend.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("User Service Test case start executed");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");
    }

    @Test
    void loadUserByUsername_UserExists() {

        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        verify(userRepository).findByEmail("test@example.com");
    }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("User Service Test case execution finished");
    }
}