package com.guru.depend.servicetest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Students;
import com.guru.depend.entity.Subject;
import com.guru.depend.entity.Teachers;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.SchoolRepository;
import com.guru.depend.repository.StudentsRepository;
import com.guru.depend.repository.TeachersRepository;
import com.guru.depend.service.SchoolService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SchoolServiceTest {

    @Mock private SchoolRepository schoolRepository;
    @Mock private StudentsRepository studentsRepository;
    @Mock private TeachersRepository teachersRepository;

    @InjectMocks private SchoolService schoolService;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("School Service Test case start executed");
    }

    @Test
    void testCreateRecord() {
        School school = new School();
        school.setId(1L);
        school.setName("School");
        school.setEmailId("email");
        school.setLocation("city");
        when(schoolRepository.save(school)).thenReturn(school);

        School savedSchool = schoolService.createRecord(school);

        verify(schoolRepository).save(school);


        assertThat(savedSchool.getId()).isEqualTo(1L);
        assertThat(savedSchool.getName()).isEqualTo("School");
    }

    @Test
    void testAllData() {
        List<School> schools =new ArrayList<>();
        School school = new School();
        school.setId(1L);
        school.setName("School");
        school.setEmailId("email");
        school.setLocation("city");
        schools.add(school);


        when(schoolRepository.findAll()).thenReturn(schools);

        List<School> allSchools = schoolService.allData();

        verify(schoolRepository).findAll();

        assertThat(allSchools).isNotNull();
        assertThat(allSchools.size()).isEqualTo(1);
        assertThat(allSchools.get(0).getName()).isEqualTo("School");

    }

    @Test
    void testGetStudentCountById() {
        when(studentsRepository.countBySchoolId(1L)).thenReturn(100L);

        Map<String, Long> studentCount = schoolService.getStudentCountById(1L);

        verify(studentsRepository).countBySchoolId(1L);

        assertThat(studentCount).isNotNull();
        assertThat(studentCount.get("total students count")).isEqualTo(100L);
    }

    @Test
    void testGetTeachersById() {
        when(teachersRepository.teacherscountBySchoolId(1L)).thenReturn(20L);

        Map<String, Long> teacherCount = schoolService.getTeachersById(1L);

        verify(teachersRepository).teacherscountBySchoolId(1L);

        assertThat(teacherCount).isNotNull();
        assertThat(teacherCount.get("total teachers count")).isEqualTo(20L);
    }

    @Test
    void testUpdateSchool() {
        School existingSchool = new School();
        existingSchool.setId(1L);
        existingSchool.setName("school");
        existingSchool.setEmailId("email");
        existingSchool.setLocation("city");

        School updatedSchool = new School();
        updatedSchool.setId(1L);
        updatedSchool.setName("Updated School");
        updatedSchool.setEmailId("updatedschool@email.com");
        updatedSchool.setLocation("Updated City");
        // Arrange: Mock the repository
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(existingSchool));
        when(schoolRepository.save(any(School.class))).thenReturn(updatedSchool);

        // Act: Call the updateSchool method
        School result = schoolService.updateSchool(1L, updatedSchool);

        // Assert: Verify the updated details
        assertNotNull(result);
        assertEquals("Updated School", result.getName());
        assertEquals("updatedschool@email.com", result.getEmailId());
        assertEquals("Updated City", result.getLocation());

    }

    @Test
    void testDeleteById() {
        School school = new School();
        school.setId(1L);
        school.setName("school");
        school.setEmailId("email");
        school.setLocation("city");
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(school));

        String result = schoolService.DeleteById(1L);

        verify(schoolRepository).delete(school);

        assertThat(result).isEqualTo("**");
    }

    @Test
    void testGetSchoolByPage() {
        School mockSchool = new School();
        mockSchool.setId(1L);
        mockSchool.setName("school");
        mockSchool.setEmailId("email");
        mockSchool.setLocation("city");
        Page<School> pageMock = new PageImpl<>(List.of(mockSchool));

        when(schoolRepository.findAll(any(Pageable.class))).thenReturn(pageMock);

        Page<School> pageResult = schoolService.getSchoolByPage(0, 10, "name");

        verify(schoolRepository).findAll(any(Pageable.class));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getContent().size()).isEqualTo(1);
    }

        @Test
        public void testGetSchoolDetailsById_Success() {
            // Given: A school with a specific id
            Long schoolId = 1L;
            School school = new School();
            school.setId(schoolId);
            school.setName("Test School");

            // When: Fetch the school by id (mock repository call)
            when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

            // Then: Ensure the school is returned correctly
            School result = schoolService.getSchoolDetailsById(schoolId);
            assertNotNull(result);
            assertEquals(schoolId, result.getId());
            assertEquals("Test School", result.getName());

            // Verify that findById was called exactly once
            verify(schoolRepository, times(1)).findById(schoolId);
        }

        @Test
        public void testGetSchoolDetailsById_NotFound() {
            Long schoolId = 1L;

            when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());

            assertThrows(UserIdNotFoundException.class, () -> schoolService.getSchoolDetailsById(schoolId));

            verify(schoolRepository, times(1)).findById(schoolId);
        }

        @Test
        public void testGetAllStudentAndTeacherBySchoolId() {

            Long schoolId = 1L;
            Students student1 = new Students();
            student1.setName("Student 1");
            Teachers teacher1 = new Teachers();
            teacher1.setName("Teacher 1");

            when(teachersRepository.findAllBySchoolId(schoolId)).thenReturn(Arrays.asList(teacher1));

            List<Object> result = schoolService.getallstudentandteacherbyschoolname(schoolId);

            assertEquals(2, result.size());  // One list for students, one for teachers

            verify(teachersRepository, times(1)).findAllBySchoolId(schoolId);
        }


    @AfterAll
    public static void toEndQuiz(){
        System.out.println("School Service Test case execution finished");
    }
}
