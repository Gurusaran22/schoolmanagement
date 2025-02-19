package com.guru.depend.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.controller.UserController;  // Replace with your actual package
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("User Controller Test case start executed");
    }

    @BeforeEach
    void setup1() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testUserLogin() throws Exception {

        ResponseDTO response = new ResponseDTO(
                "ACTIVATED",
                HttpStatus.ACCEPTED.value(),
                "****User logged In successfully****",
                null);

        when(userController.userLogin()).thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));

        mockMvc.perform(get("/api/teacher"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ACTIVATED"))
                .andExpect(jsonPath("$.message").value("****User logged In successfully****"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("User Controller Test case execution finished");
    }
}
