package com.guru.depend.controllertest;

    import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.Subject;
import com.guru.depend.service.SubjectService;
import com.guru.depend.utils.Constants;
    import org.junit.jupiter.api.AfterAll;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;
    import org.springframework.web.context.WebApplicationContext;

    import java.util.List;

    import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class SubjectControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private WebApplicationContext webApplicationContext;

        @MockBean
        private SubjectService subjectService;

        private static final ObjectMapper objectMapper = new ObjectMapper();
        @BeforeAll
        public static void toVerifyQuiz(){
            System.out.println("Subject Controller Test case start executed");
        }

        @BeforeEach
        void setup() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        }
        @BeforeEach
        void setUp() {

        }

        @Test
        void testAddSubject() throws Exception {
            Subject subject = new Subject();
            subject.setId(1L);
            subject.setSubjectName("Mathematics");

            when(subjectService.createRecord(any(Subject.class))).thenReturn(subject);

            mockMvc.perform(post("/api/subject/insert")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(subject)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.status").value(Constants.CREATED))
                    .andExpect(jsonPath("$.message").value("subject created successfully"))
                    .andExpect(jsonPath("$.data.id").value(1L))
                    .andExpect(jsonPath("$.data.subjectName").value("Mathematics"));

            verify(subjectService, times(1)).createRecord(any(Subject.class));
        }

        @Test
        void testGetAllSubjects() throws Exception {

            Subject subject1 = new Subject();
            subject1.setId(1L);
            subject1.setSubjectName("Mathematics");

            Subject subject2 = new Subject();
            subject2.setId(2L);
            subject2.setSubjectName("Science");

            when(subjectService.allData()).thenReturn(List.of(subject1, subject2));

            mockMvc.perform(get("/api/subject/retrieve"))
                    .andExpect(status().isFound())
                    .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                    .andExpect(jsonPath("$.message").value("subject retrieved successfully"))
                    .andExpect(jsonPath("$.data[0].id").value(1L))
                    .andExpect(jsonPath("$.data[0].subjectName").value("Mathematics"))
                    .andExpect(jsonPath("$.data[1].id").value(2L))
                    .andExpect(jsonPath("$.data[1].subjectName").value("Science"));

            verify(subjectService, times(1)).allData();
        }

        @Test
        void testUpdateSubject() throws Exception {
            Subject subject = new Subject();
            subject.setId(1L);
            subject.setSubjectName("Mathematics Updated");

            when(subjectService.updateSubject(eq(1L), any(Subject.class))).thenReturn(subject);

            mockMvc.perform(put("/api/subject/update/{id}", 1L)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(subject)))
                    .andExpect(status().isAccepted())
                    .andExpect(jsonPath("$.status").value(Constants.MODIFIED))
                    .andExpect(jsonPath("$.message").value("subject updated successfully"))
                    .andExpect(jsonPath("$.data.id").value(1L))
                    .andExpect(jsonPath("$.data.subjectName").value("Mathematics Updated"));

            verify(subjectService, times(1)).updateSubject(eq(1L), any(Subject.class));
        }

        @Test
        void testDeleteSubject() throws Exception {
            Long subjectId = 1L;

            when(subjectService.deleteById(subjectId)).thenReturn("Subject deleted successfully");

            mockMvc.perform(delete("/api/subject/delete/{id}", subjectId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(Constants.REMOVED))
                    .andExpect(jsonPath("$.message").value("subject deleted successfully"));

            verify(subjectService, times(1)).deleteById(subjectId);
        }

        @AfterAll
        public static void toEndQuiz(){
            System.out.println("Subject Controller Test case execution finished");
        }
    }


