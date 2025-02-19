package com.guru.depend.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.controller.AdminController;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminController adminController;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeAll
    public static void toVerifyAdmin(){
        System.out.println("Admin ControllerTest case start executed");
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    @Test

    void testAdminLogin() throws Exception {
        ResponseDTO response = new ResponseDTO(
                "ACTIVATED",  // Constants.ACTIVATED
                HttpStatus.ACCEPTED.value(),
                "****Admin logged In successfully****",
                null);

        when(adminController.adminLogin()).thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));

        mockMvc.perform(get("/api/admin"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ACTIVATED"))
                .andExpect(jsonPath("$.message").value("****Admin logged In successfully****"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
//    @Test
//    void testAdminLogin() throws Exception {
//        ResponseDTO response = new ResponseDTO(
//                "ACTIVATED",  // Constants.ACTIVATED
//                HttpStatus.ACCEPTED.value(),
//                "****Admin logged In successfully****",
//                null);
//
//        when(adminController.adminLogin()).thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));
//
//        mockMvc.perform(get("/api/admin"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.status").value("ACTIVATED"))
//                .andExpect(jsonPath("$.message").value("****Admin logged In successfully****"))
//                .andExpect(jsonPath("$.data").doesNotExist());
//    }

    @AfterAll
    public static void toEndAdmin(){
        System.out.println("Admin ControllerTest case execution finished");
    }

}