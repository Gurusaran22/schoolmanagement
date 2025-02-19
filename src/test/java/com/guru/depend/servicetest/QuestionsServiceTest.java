package com.guru.depend.servicetest;


import com.guru.depend.dto.QuestionDTO;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Quiz;
import com.guru.depend.entity.Subject;
import com.guru.depend.entity.Teachers;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.service.QuestionsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class  QuestionsServiceTest {
    @Mock
    private Teachers teachers;

    @Mock
    private Subject subject;

    @Mock
    private Quiz quiz;
    @Mock
    private QuestionsRepository questionsRepository;

    @InjectMocks
    private QuestionsService questionsService;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Questions Service Test case start executed");
    }

    @Test
    void testCreateRecord() {
        // Given: A question object
        Questions question = new Questions();
        question.setId(1L);
        question.setQuestion("What is 2 + 2?");
        question.setOption1("3");
        question.setOption2("4");
        question.setOption3("5");

        when(questionsRepository.save(question)).thenReturn(question);

        Questions result = questionsService.createRecord(question);

        verify(questionsRepository, times(1)).save(question);

        assertThat(result).isEqualTo(question);
    }

    @Test
    void testAllData() {

        List<Questions> questionsList =new ArrayList<>();
                Questions question=new Questions();
                question.setId(1L);
                question.setQuestion("Question 1");
                question.setOption1("Option 1");
                question.setOption2("Option 2");
                question.setOption3("Option 3");
                questionsList.add(question);
                question.setId(1L);
                question.setQuestion("Question 2");
                question.setOption1("Option 1");
                question.setOption2("Option 2");
                question.setOption3("Option 3");

        when(questionsRepository.findAll()).thenReturn(questionsList);

        List<Questions> result = questionsService.allData();

        verify(questionsRepository, times(1)).findAll();

        assertThat(result).isEqualTo(questionsList);
    }

    @Test
    void testGetQuestion() {
        Long questionId = 1L;
        Questions question = new Questions();
        question.setId(questionId);
        question.setQuestion("What is 2 + 2?");
        question.setOption1("3");
        question.setOption2("4");
        question.setOption3("5");

        when(questionsRepository.findById(questionId)).thenReturn(Optional.of(question));

        List<QuestionDTO> result = questionsService.getQuestion(questionId);

        verify(questionsRepository, times(1)).findById(questionId);

        assertThat(result).isNotNull() ;
        assertThat(result.get(0).getQuestion()).isEqualTo("What is 2 + 2?");
    }

    @Test
    void testDeleteById() {
        Long questionId = 1L;
        Questions question = new Questions();
        question.setId(questionId);
        question.setQuestion("What is 2 + 2?");
        question.setOption1("3");
        question.setOption2("4");
        question.setOption3("5");

        when(questionsRepository.findById(questionId)).thenReturn(Optional.of(question));

        String result = questionsService.deleteById(questionId);

        verify(questionsRepository, times(1)).delete(question);

        assertThat(result).isEqualTo("***");
    }
    @Test
    void testUpdateQuestion_Success() {
        // Given: Prepare mock question data
        Long questionId = 1L;
        Questions existingQuestion = new Questions();
        existingQuestion.setId(questionId);
        existingQuestion.setQuestion("Old Question");
        existingQuestion.setOption1("Option 1");
        existingQuestion.setOption2("Option 2");
        existingQuestion.setOption3("Option 3");
        existingQuestion.setCorrectAnswer("Option 1");
        existingQuestion.setTeachers(existingQuestion.getTeachers());
        existingQuestion.setSubject(existingQuestion.getSubject());
         existingQuestion.setQuiz(existingQuestion.getQuiz());

        // New question data for updating
        Questions updatedQuestion = new Questions();
        existingQuestion.setId(questionId);
        existingQuestion.setQuestion("new Question");
        existingQuestion.setOption1("new Option 1");
        existingQuestion.setOption2("new Option 2");
        existingQuestion.setOption3("new Option 3");
        existingQuestion.setCorrectAnswer("new Option 1");
        existingQuestion.setTeachers(existingQuestion.getTeachers());
        existingQuestion.setSubject(existingQuestion.getSubject());
        existingQuestion.setQuiz(existingQuestion.getQuiz());
        // Mock the repository to return the existing question when findById is called
        when(questionsRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));

        // When: Call the service method to update the question
        Questions result = questionsService.updateQuestion(questionId, updatedQuestion);

    }



    @AfterAll
    public static void toEndQuiz(){
        System.out.println(" Questions Service Test case execution finished");
    }
}
