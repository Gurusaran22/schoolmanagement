package com.guru.depend.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.School;
import com.guru.depend.service.SchoolService;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SchoolService schoolService;
    @BeforeAll
    public static void toVerifyQuiz(){
        System.out.println("School ControllerTest case start executed");
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testCreateSchoolWithJwtAuth() throws Exception {
        School school = new School();
        school.setId(1L);
        school.setName("Greenwood High");
        school.setEmailId("email");
        school.setLocation("city");

        when(schoolService.createRecord(school)).thenReturn(school);

        String jwtToken = "valid-jwt-token";

        mockMvc.perform(post("/api/school/insert")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(school))
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.CREATED))
                .andExpect(jsonPath("$.message").value("school created successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Greenwood High"));
    }

    @Test
    void testCreateSchool() throws Exception {
        School school = new School();
        school.setId(1L);
        school.setName("Greenwood High");
        school.setEmailId("email");
        school.setLocation("city");

        when(schoolService.createRecord(school)).thenReturn(school);

        mockMvc.perform(post("/api/school/insert")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(school)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.CREATED))
                .andExpect(jsonPath("$.message").value("school created successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Greenwood High"));
    }

    @Test
    void testGetAllSchools() throws Exception {

        School school1 = new School();
        school1.setId(1L);
        school1.setName("Greenwood High");
        school1.setEmailId("email");
        school1.setLocation("city");
        School school2 = new School();
        school2.setId(2L);
        school2.setName("High");
        school2.setEmailId("email1");
        school2.setLocation("citya");
        when(schoolService.allData()).thenReturn(List.of(school1, school2));

        mockMvc.perform(get("/api/school/retrieve"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("school retrieved successfully"))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Test
    void testGetSchoolDetailsById() throws Exception {
        School school = new School();
        school.setId(1L);
        school.setName("Greenwood High");
        school.setEmailId("email");
        school.setLocation("city");

        when(schoolService.getSchoolDetailsById(1L)).thenReturn(school);

        mockMvc.perform(get("/api/school/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("school retrieved successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Greenwood High"));
    }

    @Test
    void testUpdateSchool() throws Exception {
        School school = new School();
        school.setId(1L);
        school.setName("Greenwood High");
        school.setEmailId("email");
        school.setLocation("city");

        when(schoolService.updateSchool(1L, school)).thenReturn(school);

        mockMvc.perform(put("/api/school/update/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(school)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("school updated successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Greenwood High"));
    }

    @Test
    void testDeleteSchoolById() throws Exception {

        when(schoolService.DeleteById(1L)).thenReturn(String.valueOf(true));

        mockMvc.perform(delete("/api/school/delete/{id}", 1L))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status").value(Constants.REMOVED))
                .andExpect(jsonPath("$.message").value("school deleted successfully"));
    }

    @Test
    void testGetSchoolByPage() throws Exception {

        when(schoolService.getSchoolByPage(0, 10, "name")).thenReturn(Page.empty());

        mockMvc.perform(get("/api/school/page")
                        .param("pageIndex", "0")
                        .param("pageSize", "10")
                        .param("field", "name"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("pagenation done successfully"));
    }

        @Test
        public void testGetStudentCountById() throws Exception {
            // Given: School ID and expected student count
            Long schoolId = 1L;
            int studentCount = 100;

            // When: Call the controller method via MockMvc
            mockMvc.perform(get("/api/school/student_count/{id}", schoolId))
                    .andExpect(status().isFound())  // Expect HTTP 302 (FOUND)
                    .andExpect(jsonPath("$.status").value("Retrived successfully."))
                    .andExpect(jsonPath("$.message").value("student counted successfully"));

            // Verify that the service method was called exactly once
            verify(schoolService, times(1)).getStudentCountById(schoolId);
        }

        @Test
        public void testGetTeachersCountById() throws Exception {
            // Given: School ID and expected teacher count
            Long schoolId = 1L;
            int teacherCount = 20;

            mockMvc.perform(get("/api/school/teacher_count/{id}", schoolId))
                    .andExpect(status().isFound())  // Expect HTTP 302 (FOUND)
                    .andExpect(jsonPath("$.status").value("Retrived successfully."))
                    .andExpect(jsonPath("$.message").value("teacher counted successfully"));

            verify(schoolService, times(1)).getTeachersById(schoolId);
        }

    @AfterAll
    public static void toEndQuiz(){
        System.out.println("School ControllerTest case execution finished");
    }
}

