package com.guru.depend.servicetest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.guru.depend.dto.JwtAuthenticationResponse;
import com.guru.depend.dto.RefreshTokenRequest;
import com.guru.depend.dto.SignInRequest;
import com.guru.depend.dto.SignUpRequest;
import com.guru.depend.entity.User;
import com.guru.depend.enums.Role;
import com.guru.depend.exception.InvalidJwtException;
import com.guru.depend.repository.UserRepository;
import com.guru.depend.service.JwtService;
import com.guru.depend.service.LoginService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    private SignUpRequest signUpRequest;
    private SignInRequest signInRequest;
    private User user;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Login Service Test case start executed");
    }

    @BeforeEach
    void setUp() {
        signUpRequest = new SignUpRequest("test@email.com", "password123",Role.ADMIN);
        signInRequest = new SignInRequest("test@email.com", "password123");
    }

    @Test
    void testStudentSignUp() {
        user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.STUDENT);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = loginService.studentSignUp(signUpRequest);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(Role.STUDENT, savedUser.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAdminSignUp() {
        user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.ADMIN);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = loginService.adminSignUp(signUpRequest);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(Role.ADMIN, savedUser.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testTeacherSignUp() {
        user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.TUTOR);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = loginService.teacherSignUp(signUpRequest);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(Role.TUTOR, savedUser.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSignInInvalidEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            loginService.signIn(signInRequest);
        });
        assertEquals("Incorrect email ", exception.getMessage());
    }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("Login Service Test case execution finished");
    }
}



