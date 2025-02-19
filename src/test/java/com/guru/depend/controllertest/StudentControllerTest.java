package com.guru.depend.controllertest;
    import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.Students;
import com.guru.depend.service.StudentsService;
import com.guru.depend.utils.Constants;
    import org.junit.jupiter.api.AfterAll;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Map;

    import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class StudentControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private StudentsService studentsService;

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private Students student;
        @BeforeAll
        public static void toVerifyQuiz(){
            System.out.println("Student Controller Test case start executed");
        }

        @BeforeEach
        void setUp() {
            student = new Students();
            student.setId(1L);
            student.setName("John Doe");
            student.setEmailId("john.doe@example.com");
            student.setGender("male");
        }

        @Test
        void testCreateStudent() throws Exception {
            when(studentsService.createRecord(student)).thenReturn(student);

            mockMvc.perform(post("/api/students/insert")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(student)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.status").value(Constants.CREATED))
                    .andExpect(jsonPath("$.message").value("student created successfully"))
                    .andExpect(jsonPath("$.data.id").value(1L))
                    .andExpect(jsonPath("$.data.name").value("John Doe"));

            verify(studentsService, times(1)).createRecord(student);
        }

        @Test
        void testGetAllStudents() throws Exception {

            when(studentsService.allData()).thenReturn(List.of(student));

            mockMvc.perform(get("/api/students/retrieve"))
                    .andExpect(status().isFound())
                    .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                    .andExpect(jsonPath("$.message").value("student retrieved successfully"))
                    .andExpect(jsonPath("$.data[0].id").value(1L))
                    .andExpect(jsonPath("$.data[0].name").value("John Doe"));

            verify(studentsService, times(1)).allData();
        }

        @Test
        void testGetStudentDetails() throws Exception {

            when(studentsService.getStudentDetails(1L)).thenReturn(student);


            mockMvc.perform(get("/api/students/{id}", 1L))
                    .andExpect(status().isFound())
                    .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                    .andExpect(jsonPath("$.message").value("student details retrieved successfully"))
                    .andExpect(jsonPath("$.data.id").value(1L))
                    .andExpect(jsonPath("$.data.name").value("John Doe"));

            verify(studentsService, times(1)).getStudentDetails(1L);
        }

        @Test
        void testUpdateStudent() throws Exception {

            Students updatedStudent = new Students();
            updatedStudent.setId(1L);
            updatedStudent.setName("John Updated");
            updatedStudent.setEmailId("john.updated@example.com");
            updatedStudent.setGender("male");

            when(studentsService.updateStudent(1L, updatedStudent)).thenReturn(updatedStudent);

            mockMvc.perform(put("/api/students/update/{id}", 1L)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(updatedStudent)))
                    .andExpect(status().isAccepted())
                    .andExpect(jsonPath("$.status").value(Constants.MODIFIED))
                    .andExpect(jsonPath("$.message").value("student updated successfully"))
                    .andExpect(jsonPath("$.data.id").value(1L))
                    .andExpect(jsonPath("$.data.name").value("John Updated"));

            verify(studentsService, times(1)).updateStudent(1L, updatedStudent);
        }

        @Test
        void testDeleteStudent() throws Exception {

            when(studentsService.deleteById(1L)).thenReturn("Deleted");

            mockMvc.perform(delete("/api/students/delete/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(Constants.REMOVED))
                    .andExpect(jsonPath("$.message").value("student deleted successfully"));

            verify(studentsService, times(1)).deleteById(1L);
        }

        @Test
        void testGetStudentMark() throws Exception {

            when(studentsService.evaluateStudentMark(1L)).thenReturn(85);

            mockMvc.perform(get("/api/students/studentmark/{id}", 1L))
                    .andExpect(status().isFound())
                    .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                    .andExpect(jsonPath("$.message").value("student mark retrieved successfully"))
                    .andExpect(jsonPath("$.data").value(85));

            verify(studentsService, times(1)).evaluateStudentMark(1L);
        }

            @Test
            public void testAllMarks_Success() throws Exception {
                Long studentId = 1L;
                int expectedMarks = 85;

                // Mock the service method
                when(studentsService.allMarks(studentId)).thenReturn(expectedMarks);

                // Perform the GET request and validate the response
                mockMvc.perform(get("/api/students/mark/{id}", studentId))
                        .andExpect(status().isOk())
                        .andExpect(content().string(String.valueOf(expectedMarks)));  // Expects marks as plain integer
            }

            @Test
            public void testAllStudentsMarks_Success() throws Exception {
                // Mock the response data
                List<Students> students = new ArrayList<>();
                        Students stud=new Students();
                        stud.setId(1L);
                        stud.setName("guru");
                        stud.setGender("male");
                        stud.setEmailId("guru2223");
                        stud.setSchool(stud.getSchool());
                        students.add(stud);
                        stud.setId(2L);
                        stud.setName("saran");
                        stud.setGender("male");
                        stud.setEmailId("guru2223");
                        stud.setSchool(stud.getSchool());
                        students.add(stud);

                // Perform the GET request and validate the response
                mockMvc.perform(get("/api/students/allmarks"))
                        .andExpect(status().isFound())
                        .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                        .andExpect(jsonPath("$.message").value("student mark retrieved successfully"));

            }



        @AfterAll
        public static void toEndQuiz(){
            System.out.println("Student Controller Test case execution finished");
        }
    }


