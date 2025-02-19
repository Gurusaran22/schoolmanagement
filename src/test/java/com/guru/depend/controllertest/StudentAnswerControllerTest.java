package com.guru.depend.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.controller.StudentAnswerController;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.service.StudentAnswerService;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentAnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StudentAnswerService studentAnswerService;

    @InjectMocks
    private StudentAnswerController studentAnswerController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private StudentAnswer mockStudentAnswer;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Student Answer ControllerTest case start executed");
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @BeforeEach
    void setUp() {
        mockStudentAnswer = new StudentAnswer();
        mockStudentAnswer.setId(1L);
        mockStudentAnswer.setSanswer("Sample answer");
        mockStudentAnswer.setStudents(mockStudentAnswer.getStudents());
        mockStudentAnswer.setQuestions(mockStudentAnswer.getQuestions());
    }

    @Test
    void testAddStudentAnswer() throws Exception {

        when(studentAnswerService.addRecord(any(StudentAnswer.class))).thenReturn(mockStudentAnswer);

        mockMvc.perform(post("/api/studentanswers/insert")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(mockStudentAnswer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.CREATED))
                .andExpect(jsonPath("$.message").value("student answered successfully"))
                .andExpect(jsonPath("$.data.id").value(1L));

        Mockito.verify(studentAnswerService, times(1)).addRecord(any(StudentAnswer.class));
    }

    @Test
    void testGetAllStudentAnswers() throws Exception {

        when(studentAnswerService.allData()).thenReturn(java.util.Collections.singletonList(mockStudentAnswer));

        mockMvc.perform(get("/api/studentanswers/retrieve"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("studentanswers retrieved successfully"))
                .andExpect(jsonPath("$.data[0].id").value(1L));

        Mockito.verify(studentAnswerService, times(1)).allData();
    }

    @Test
    void testGetStudentAnswerById() throws Exception {

        when(studentAnswerService.getStudentDetails(1L)).thenReturn(mockStudentAnswer);

        mockMvc.perform(get("/api/studentanswers/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("studentanswer retrieved successfully"))
                .andExpect(jsonPath("$.data.id").value(1L));

        Mockito.verify(studentAnswerService, times(1)).getStudentDetails(1L);
    }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("Student Answer Controller Test case execution finished");
    }
}

