package com.guru.depend.servicetest;

import com.guru.depend.entity.Questions;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.entity.Students;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.repository.StudentAnswerRespository;
import com.guru.depend.repository.StudentsRepository;
import com.guru.depend.service.StudentsService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentsServiceTest {

    @Mock
    private StudentsRepository studentsRepository;

    @Mock
    private StudentAnswerRespository studentAnswerRespository;

    @Mock
    private QuestionsRepository questionsRepository;

    @InjectMocks
    private StudentsService studentsService;

    private Students student;
    private StudentAnswer studentAnswer;
    private Questions question;

    @BeforeAll
    public static void toVerifyQuiz() {
        System.out.println("Students Service Test case start executed");
    }

    @BeforeEach
    void setUp() {
        // Initialize test objects
        student = new Students();
        student.setId(1L);
        student.setName("John Doe");

        question = new Questions();
        question.setCorrectAnswer("42");

        studentAnswer = new StudentAnswer();
        studentAnswer.setQuestions(question);
        studentAnswer.setSanswer("42");
    }

    @Test
    void testCreateRecord() {

        when(studentsRepository.save(any(Students.class))).thenReturn(student);

        Students result = studentsService.createRecord(student);

        Mockito.verify(studentsRepository, times(1)).save(student);

        assertEquals(student, result);
    }

    @Test
    void testAllData() {

        List<Students> studentsList = Arrays.asList(student);
        when(studentsRepository.findAll()).thenReturn(studentsList);

        List<Students> result = studentsService.allData();

        Mockito.verify(studentsRepository, times(1)).findAll();

        assertEquals(studentsList, result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetStudentDetails() {

        when(studentsRepository.findById(1L)).thenReturn(Optional.of(student));

        Students result = studentsService.getStudentDetails(1L);

        assertEquals(student, result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testEvaluateStudentMark() {

        List<StudentAnswer> answers = Arrays.asList(studentAnswer);
        when(studentAnswerRespository.findBySchoolId(1L)).thenReturn(answers);

        int result = studentsService.evaluateStudentMark(1L);

        assertEquals(1, result);
    }

    @Test
    void testAllMarks() {

        List<StudentAnswer> answers = Arrays.asList(studentAnswer);
        when(studentAnswerRespository.findAllByStudentsId(1L)).thenReturn(answers);

        int result = studentsService.allMarks(1L);

        assertEquals(1, result);
    }

    @Test
    void testAllStudentsMark() {

        List<Students> studentsList = Arrays.asList(student);
        Map<Long, Integer> studentMarks = new HashMap<>();
        studentMarks.put(1L, 1);
        when(studentsRepository.findAll()).thenReturn(studentsList);
        when(studentAnswerRespository.findAllByStudentsId(1L)).thenReturn(Arrays.asList(studentAnswer));

        Map<Long, Integer> result = studentsService.allstudentsMark();

        assertEquals(studentMarks, result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(1L));
    }

    @Test
    void testUpdateStudent() {

        when(studentsRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentsRepository.save(student)).thenReturn(student);

        Students result = studentsService.updateStudent(1L, student);

        Mockito.verify(studentsRepository, times(1)).save(student);

        assertEquals(student, result);
    }

    @Test
    void testDeleteById() {

        when(studentsRepository.findById(1L)).thenReturn(Optional.of(student));

        String result = studentsService.deleteById(1L);

        Mockito.verify(studentsRepository, times(1)).delete(student);

        assertEquals("****", result);
    }

    @AfterAll
    public static void toEndQuiz() {
        System.out.println("Students Service Test case execution finished");
    }
}
