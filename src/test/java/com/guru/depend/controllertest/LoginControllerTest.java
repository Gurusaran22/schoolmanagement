package com.guru.depend.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.controller.LoginController;
import com.guru.depend.dto.SignUpRequest;
import com.guru.depend.entity.User;
import com.guru.depend.enums.Role;
import com.guru.depend.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private LoginService authenticationService;  // Mocking LoginService

    @InjectMocks
    private LoginController loginController;  // Injecting the mock into LoginController

    private MockMvc mockMvc;

    private User user;  // Declaring user object

    private ObjectMapper objectMapper;  // Declaring ObjectMapper manually

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();  // Setting up MockMvc

        // Initialize the ObjectMapper manually
        objectMapper = new ObjectMapper();

        // Initialize user before the test
        user = new User();
        user.setEmail("admin@domain.com");
        user.setPassword("password");
        user.setRole(Role.ADMIN);
    }

    @Test
    void testAdminSignUp() throws Exception {
        // Given
        SignUpRequest signUpRequest = new SignUpRequest("admin@domain.com", "password", Role.ADMIN);

        // Mocking the service method to return the initialized 'user'
        when(authenticationService.adminSignUp(any(SignUpRequest.class)))
                .thenReturn(user);

        // When & Then
        mockMvc.perform(post("/api/auth/admin-signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isCreated())  // Expecting HTTP 201
                .andExpect(jsonPath("$.message").value("admin created successfully"))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()));

        verify(authenticationService).adminSignUp(any(SignUpRequest.class));  // Verifying method call
    }


    @Test
    void testStudentSignUp() throws Exception {
        // Given
        SignUpRequest signUpRequest = new SignUpRequest("student@domain.com", "password", Role.STUDENT);

        // Mock the service to return the mock student
        when(authenticationService.studentSignUp(any(SignUpRequest.class)))
                .thenReturn(user);

        // When & Then: Perform the request and validate the response
        mockMvc.perform(post("/api/auth/student-signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signUpRequest)))  // Convert the request to JSON
                .andExpect(status().isCreated())  // Expecting HTTP status 201 (Created)
                .andExpect(jsonPath("$.message").value("student created successfully"))  // Checking the response message
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()));  // Verifying the HTTP status in the response

        verify(authenticationService).studentSignUp(any(SignUpRequest.class));  // Verifying the method call on the service
    }

    @Test
    void testTeacherSignUp() throws Exception {
        // Given
        SignUpRequest signUpRequest = new SignUpRequest("teacher@domain.com", "password", Role.TUTOR);

        // Mock the service to return the mock teacher
        when(authenticationService.teacherSignUp(any(SignUpRequest.class)))
                .thenReturn(user);

        // When & Then: Perform the request and validate the response
        mockMvc.perform(post("/api/auth/teacher-signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signUpRequest)))  // Convert the request to JSON
                .andExpect(status().isCreated())  // Expecting HTTP status 201 (Created)
                .andExpect(jsonPath("$.message").value("teacher created successfully"))  // Checking the response message
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()));  // Verifying the HTTP status in the response
        verify(authenticationService).teacherSignUp(any(SignUpRequest.class));  // Verifying the method call on the service
    }
}
