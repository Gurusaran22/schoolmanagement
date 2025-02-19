package com.guru.depend.servicetest;

import com.guru.depend.entity.Quiz;
import com.guru.depend.entity.Subject;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.SubjectRepository;
import com.guru.depend.service.SubjectService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
    public class SubjectServiceTest {

        @Mock
        private SubjectRepository subjectRepository;

        @InjectMocks
        private SubjectService subjectService;

        @BeforeAll
        public static void setupBeforeAll() {
            System.out.println("Subject Service Test case start executed");
        }

        @Test
        void testCreateRecord() {

            Subject subject = new Subject();
            subject.setId(1L);
            subject.setSubjectName("Mathematics");

            when(subjectRepository.save(subject)).thenReturn(subject);

            Subject savedSubject = subjectService.createRecord(subject);

            verify(subjectRepository, times(1)).save(subject);

            assertThat(savedSubject).isNotNull();
            assertThat(savedSubject.getId()).isEqualTo(1L);
            assertThat(savedSubject.getSubjectName()).isEqualTo("Mathematics");
        }

        @Test
        void testAllData() {

            List<Subject> subjects = new ArrayList<>();
            Subject subject1 = new Subject();
            subject1.setId(1L);
            subject1.setSubjectName("Mathematics");
            subjects.add(subject1);

            when(subjectRepository.findAll()).thenReturn(subjects);

            List<Subject> allSubjects = subjectService.allData();

            verify(subjectRepository, times(1)).findAll();

            assertThat(allSubjects).isNotNull();
            assertThat(allSubjects.size()).isEqualTo(1);
            assertThat(allSubjects.get(0).getSubjectName()).isEqualTo("Mathematics");
        }

        @Test
        void testUpdateSubject() {

            Subject existingSubject = new Subject();
            existingSubject.setId(1L);
            existingSubject.setSubjectName("Mathematics");

            Subject updatedSubject = new Subject();
            updatedSubject.setId(1L);
            updatedSubject.setSubjectName("Updated Mathematics");

            when(subjectRepository.findById(1L)).thenReturn(Optional.of(existingSubject));

            when(subjectRepository.save((updatedSubject))).thenReturn(updatedSubject);

            Subject result = subjectService.updateSubject(1L, updatedSubject);

            verify(subjectRepository, times(1)).save(updatedSubject);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getSubjectName()).isEqualTo("Updated Mathematics");
        }

        @Test
        void testDeleteById() {

            Subject subject = new Subject();
            subject.setId(1L);
            subject.setSubjectName("Mathematics");

            when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

            String result = subjectService.deleteById(1L);

            verify(subjectRepository, times(1)).delete(subject);

            assertThat(result).isEqualTo("****");
        }

        @AfterAll
        public static void cleanupAfterAll() {
            System.out.println("Subject Service Test case execution finished");
        }
    }


