package com.guru.depend.servicetest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.guru.depend.entity.Teachers;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.TeachersRepository;
import com.guru.depend.service.TeachersService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TeachersServiceTest {

    @InjectMocks
    private TeachersService teachersService;

    @Mock
    private TeachersRepository teachersRepository;

    private Teachers teacher;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("Teachers Service  case start executed");
    }

    @BeforeEach
    void setUp() {
        teacher = new Teachers();
        teacher.setId(1L);
        teacher.setName("John Doe");
        teacher.setSubject(teacher.getSubject());
    }

    @Test
    void testCreateRecord() {
        when(teachersRepository.save(any(Teachers.class))).thenReturn(teacher);

        Teachers savedTeacher = teachersService.createRecord(teacher);

        assertNotNull(savedTeacher);
        assertEquals(teacher.getId(), savedTeacher.getId());
        assertEquals(teacher.getName(), savedTeacher.getName());
        verify(teachersRepository, times(1)).save(teacher);
    }

    @Test
    void testAllData() {
        List<Teachers> teachersList = List.of(teacher);
        when(teachersRepository.findAll()).thenReturn(teachersList);

        List<Teachers> result = teachersService.allData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(teacher.getName(), result.get(0).getName());
        verify(teachersRepository, times(1)).findAll();
    }

    @Test
    void testGetTeachersDetails_Success() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teachers result = teachersService.getTeachersDetails(1L);

        assertNotNull(result);
        assertEquals(teacher.getId(), result.getId());
        assertEquals(teacher.getName(), result.getName());
    }

    @Test
    void testGetTeachersDetails_TeacherNotFound() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.empty());

        UserIdNotFoundException exception = assertThrows(UserIdNotFoundException.class, () -> {
            teachersService.getTeachersDetails(1L);
        });

        assertEquals("teacher not found by this id", exception.getMessage());
    }

    @Test
    void testUpdateTeacher_Success() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teachersRepository.save(any(Teachers.class))).thenReturn(teacher);

        Teachers updatedTeacher = teachersService.updateTeacher(1L, teacher);

        assertNotNull(updatedTeacher);
        assertEquals(teacher.getId(), updatedTeacher.getId());
        verify(teachersRepository, times(1)).findById(1L);
        verify(teachersRepository, times(1)).save(teacher);
    }

    @Test
    void testUpdateTeacher_TeacherNotFound() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.empty());

        UserIdNotFoundException exception = assertThrows(UserIdNotFoundException.class, () -> {
            teachersService.updateTeacher(1L, teacher);
        });

        assertEquals("id not found", exception.getMessage());
    }

    @Test
    void testDeleteTeacher_Success() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.of(teacher));

        String result = teachersService.deleteById(1L);

        assertEquals("****", result);
        verify(teachersRepository, times(1)).findById(1L);
        verify(teachersRepository, times(1)).delete(teacher);
    }

    @Test
    void testDeleteTeacher_TeacherNotFound() {
        when(teachersRepository.findById(1L)).thenReturn(Optional.empty());

        UserIdNotFoundException exception = assertThrows(UserIdNotFoundException.class, () -> {
            teachersService.deleteById(1L);
        });
        assertEquals("id not found", exception.getMessage());
    }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("Teachers Service Test case execution finished");
    }
}
