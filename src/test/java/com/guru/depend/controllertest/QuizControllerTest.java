package com.guru.depend.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.guru.depend.entity.Quiz;
import com.guru.depend.service.QuizService;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
    @AutoConfigureMockMvc
    public class QuizControllerTest {

        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        @Autowired
        private WebApplicationContext webApplicationContext;

        @MockBean
        private QuizService quizService;

        private MockMvc mockMvc;

        @BeforeAll
        public static void setupAll() {
            System.out.println("Quiz ControllerTest case start executed");
        }

        @BeforeEach
        void setup() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        }

        @Test
        void testAddQuiz() throws Exception {
            Quiz quiz = new Quiz();
            quiz.setId(1L);
            quiz.setTitle("Math Quiz");

            when(quizService.createRecord(any(Quiz.class))).thenReturn(quiz);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/insert")
                            .contentType("application/json")
                            .content(OBJECT_MAPPER.writeValueAsBytes(quiz)))
                    .andExpect(status().isCreated()) // Expecting 201 CREATED status
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.CREATED))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("quiz created successfully"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1L))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value("Math Quiz"));

            verify(quizService, times(1)).createRecord(any(Quiz.class));
        }


        @AfterAll
        public static void tearDown() {
            System.out.println("Quiz ControllerTest case execution finished");
        }
    }


