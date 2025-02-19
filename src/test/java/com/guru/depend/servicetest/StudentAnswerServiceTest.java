package com.guru.depend.servicetest;

import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.repository.StudentAnswerRespository;
import com.guru.depend.service.StudentAnswerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
    public class StudentAnswerServiceTest {

        @Mock
        private StudentAnswerRespository studentAnswerRepository;

        @InjectMocks
        private StudentAnswerService studentAnswerService;

    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Student Answer Service Test case start executed");
    }

        @Test
        void testAddRecord() {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setId(1L);
            studentAnswer.setSanswer("Sample Answer");

            when(studentAnswerRepository.save(any(StudentAnswer.class))).thenReturn(studentAnswer);

            StudentAnswer result = studentAnswerService.addRecord(studentAnswer);

            Mockito.verify(studentAnswerRepository, times(1)).save(studentAnswer);

            assertThat(result).isEqualTo(studentAnswer);
        }

        @Test
        void testAllData() {

            List<StudentAnswer> studentAnswers = new ArrayList<>();
                      StudentAnswer stdanswer=new StudentAnswer();
            stdanswer.setId(1L);
            stdanswer.setSanswer("answer1");
            studentAnswers.add(stdanswer);
            stdanswer.setId(2L);
            stdanswer.setSanswer("answer2");
            studentAnswers.add(stdanswer);

            when(studentAnswerRepository.findAll()).thenReturn(studentAnswers);

            List<StudentAnswer> result = studentAnswerService.allData();

            Mockito.verify(studentAnswerRepository, times(1)).findAll();

            assertThat(result).isEqualTo(studentAnswers);
            assertThat(result.size()).isEqualTo(2);
        }

        @Test
        public void testGetStudentDetails_Success() {
            // Given: Prepare mock student answer
            Long studentId = 1L;
            StudentAnswer mockStudentAnswer = new StudentAnswer();
            mockStudentAnswer.setId(1L);
            mockStudentAnswer.setSanswer("3");
            mockStudentAnswer.setStudents(mockStudentAnswer.getStudents());
            mockStudentAnswer.setQuestions(mockStudentAnswer.getQuestions());
            mockStudentAnswer.setQuiz(mockStudentAnswer.getQuiz());

            // Mock the repository behavior: When findById is called with the studentId, return the mock data
            when(studentAnswerRepository.findById(studentId)).thenReturn(Optional.of(mockStudentAnswer));

            // When: Call the service method to get the student details
            StudentAnswer result = studentAnswerService.getStudentDetails(studentId);

            // Then: Assert that the result is the same as the mock object
            assertEquals(studentId, result.getId());
            assertEquals("3", result.getSanswer());
            assertEquals(mockStudentAnswer.getStudents(), result.getStudents());
            assertEquals(mockStudentAnswer.getQuestions(), result.getQuestions());
            assertEquals(mockStudentAnswer.getQuiz(),result.getQuiz());
        }
    @AfterAll
    public static void toEndQuiz(){
        System.out.println("Student Answer Service Test case execution finished");
    }
    }
