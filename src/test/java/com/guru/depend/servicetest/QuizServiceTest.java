package com.guru.depend.servicetest;


import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Quiz;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.repository.QuizRepository;
import com.guru.depend.service.QuestionsService;
import com.guru.depend.service.QuizService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizRepository quizrepository ;
    @InjectMocks
    private QuizService quizService ;
    @Mock
    private QuestionsRepository questionsRepository;
    @InjectMocks
    private QuestionsService questionsService;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Quiz Service Test case start executed");
    }

    @Test
    void testCreateRecord() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setTitle("maths");

        when(quizrepository.save(any(Quiz.class))).thenReturn(quiz);

        Quiz savedQuiz = quizService.createRecord(quiz);

        verify(quizrepository, times(1)).save(quiz);

        assertThat(savedQuiz).isNotNull();
        assertThat(savedQuiz.getId()).isEqualTo(1L);
        assertThat(savedQuiz.getTitle()).isEqualTo("maths");
    }
//    @Test
//    void testDeleteById_Success() {
//        Long questionId = 1L;
//        Questions question = new Questions();
//        question.setId(questionId);
//
//        when(questionsRepository.findById(questionId)).thenReturn(Optional.of(question));
//
//        String result = questionsService.deleteById(questionId);
//
//        verify(questionsRepository, times(1)).delete(question);
//        assertThat(result).isEqualTo("***");
//    }

//
//    @Test
//    void testDeleteById_Success() {
//        // Given: Prepare a mock question object
//        Long questionId = 1L;
//        Questions question = new Questions();
//        question.setId(questionId);
//
//        // Mock the repository behavior: When findById is called with the questionId, return the mock question
//        when(questionsRepository.findById(questionId)).thenReturn(Optional.of(question));
//
//        // When: Call the service method to delete the question by id
//        String result = questionsService.deleteById(questionId);
//
//        // Then: Verify that delete was called once on the repository
//        verify(questionsRepository, times(1)).delete(question);
//
//        // Assert that the returned string is "***"
//        assertThat(result).isEqualTo("***");

       @AfterAll
    public static void toEndQuiz(){
        System.out.println("Quiz Service Test case execution finished");
    }
}

