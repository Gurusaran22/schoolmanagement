package com.guru.depend.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.guru.depend.dto.QuestionDTO;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Subject;
import com.guru.depend.entity.Teachers;
import com.guru.depend.enums.Role;
import com.guru.depend.service.QuestionsService;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionsControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionsService questionsService;

    private Questions mockQuestion;

    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Question Controller testing started");
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @BeforeEach
    void setUp() {
        mockQuestion = new Questions();
        mockQuestion.setId(1L);
        mockQuestion.setQuestion("Sample question");
        mockQuestion.setOption1("1");
        mockQuestion.setOption2("2");
        mockQuestion.setOption3("3");
        mockQuestion.setCorrectAnswer("2");
        mockQuestion.setTeachers(mockQuestion.getTeachers());
        mockQuestion.setSubject(mockQuestion.getSubject());
        mockQuestion.setQuiz(mockQuestion.getQuiz());
    }
    @Test
    void testCreateRecord() throws Exception {
        when(questionsService.createRecord(any(Questions.class))).thenReturn(mockQuestion);

        mockMvc.perform(post("/api/questions/insert")
                        .contentType("application/json")
                        .content(OBJECT_MAPPER.writeValueAsBytes(mockQuestion)))
                .andExpect(status().isCreated())  // Expect 201 Created
                .andExpect(jsonPath("$.status").value(Constants.CREATED))
                .andExpect(jsonPath("$.message").value("question created successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.question").value("Sample question"));

        verify(questionsService, times(1)).createRecord(any(Questions.class));
    }

    @Test
    void testAllData() throws Exception {
        when(questionsService.allData()).thenReturn(java.util.Collections.singletonList(mockQuestion));

        mockMvc.perform(get("/api/questions/retrieve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("question retrieved successfully"));

        verify(questionsService, times(1)).allData();
    }

    @Test
    void testUpdateQuestions() throws Exception {
        when(questionsService.updateQuestion(1L, mockQuestion)).thenReturn(mockQuestion);

        mockMvc.perform(put("/api/questions/update/{id}", 1L)
                        .contentType("application/json")
                        .content(OBJECT_MAPPER.writeValueAsBytes(mockQuestion)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status").value(Constants.MODIFIED))
                .andExpect(jsonPath("$.message").value("question updated successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.question").value("Sample question"));

        verify(questionsService, times(1)).updateQuestion(1L, mockQuestion);
    }

    @Test
    void testDeleteByIdRecord() throws Exception {

        when(questionsService.deleteById(1L)).thenReturn("**");

        mockMvc.perform(delete("/api/questions/delete/{id}", 1L))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status").value(Constants.REMOVED))
                .andExpect(jsonPath("$.message").value("question deleted successfully"))
                .andExpect(jsonPath("$.data").value("**"));

        verify(questionsService, times(1)).deleteById(1L);
    }

        @Test
        public void testGetQuestion_Success() throws Exception {
            // Given: Mocked question data
            Long questionId = 1L;
            Questions question = new Questions();
            question.setId(questionId);
            question.setQuestion("Sample Question");
            question.setOption1("Option A");
            question.setOption2("Option B");
            question.setOption3("Option C");
            question.setCorrectAnswer("Option A");

         mockMvc.perform(get("/api/questions/question/{id}", questionId))
                    .andExpect(status().isOk())  // Expect HTTP 200 (OK)
                    .andExpect(jsonPath("$.status").value("Retrived successfully."))
                    .andExpect(jsonPath("$.message").value("question retrieved successfully"));

          verify(questionsService, times(1)).getQuestion(questionId);
        }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("Question Controller testing ended");
    }
}

