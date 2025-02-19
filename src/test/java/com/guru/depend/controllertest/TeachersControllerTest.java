package com.guru.depend.controllertest;

    import com.guru.depend.controller.TeachersController;
    import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.Teachers;
import com.guru.depend.service.TeachersService;
import com.guru.depend.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

    import java.util.Arrays;
    import java.util.List;

    import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import com.guru.depend.dto.ResponseDTO;
    import com.guru.depend.entity.Teachers;
    import com.guru.depend.service.TeachersService;
    import com.guru.depend.utils.Constants;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.AfterAll;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.http.HttpStatus;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;
    import org.springframework.web.context.WebApplicationContext;

    import static org.mockito.Mockito.*;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TeachersControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TeachersService teachersService;

    private MockMvc mockMvc;

    @BeforeAll
    public static void testStart() {
        System.out.println("Teachers Controller Test case start executed");
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testCreateRecord() throws Exception {
        Teachers teacher = new Teachers();
        teacher.setId(1L);
        teacher.setName("John Doe");

        when(teachersService.createRecord(any(Teachers.class))).thenReturn(teacher);

        mockMvc.perform(post("/api/teachers/insert")
                        .contentType("application/json")
                        .content(OBJECT_MAPPER.writeValueAsBytes(teacher)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.CREATED))
                .andExpect(jsonPath("$.message").value("****teacher Inserted successfully****"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("John Doe"));

        verify(teachersService, times(1)).createRecord(any(Teachers.class));
    }

    @Test
    void testAllData() throws Exception {
        Teachers teacher1 = new Teachers();
        teacher1.setId(1L);
        teacher1.setName("John Doe");

        Teachers teacher2 = new Teachers();
        teacher2.setId(2L);
        teacher2.setName("Jane Doe");

        List<Teachers> teachersList = Arrays.asList(teacher1, teacher2);

        when(teachersService.allData()).thenReturn(teachersList);

        mockMvc.perform(get("/api/teachers/retrieve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("**** teachers retrieved successfully****"))
                .andExpect(jsonPath("$.data.length()").value(2));

        verify(teachersService, times(1)).allData();
    }

    @Test
    void testGetTeacherDetails() throws Exception {

        Teachers teacher = new Teachers();
        teacher.setId(1L);
        teacher.setName("John Doe");

        when(teachersService.getTeachersDetails(1L)).thenReturn(teacher);

        mockMvc.perform(get("/api/teachers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Constants.RETRIEVED))
                .andExpect(jsonPath("$.message").value("****teacher retrieved successfully****"))
                .andExpect(jsonPath("$.data.name").value("John Doe"));

        verify(teachersService, times(1)).getTeachersDetails(1L);
    }

    @Test
    void testUpdateTeacher() throws Exception {
        Teachers teacher = new Teachers();
        teacher.setId(1L);
        teacher.setName("John Doe Updated");

        when(teachersService.updateTeacher(1L, teacher)).thenReturn(teacher);

        mockMvc.perform(put("/api/teachers/update/{id}", 1L)
                        .contentType("application/json")
                        .content(OBJECT_MAPPER.writeValueAsBytes(teacher)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(Constants.MODIFIED))
                .andExpect(jsonPath("$.message").value("****teacher updated successfully****"));

        verify(teachersService, times(1)).updateTeacher(1L, teacher);
    }

    @Test
    void testDeleteTeacher() throws Exception {

        Long teacherId = 1L;

        when(teachersService.deleteById(teacherId)).thenReturn("****");

        mockMvc.perform(delete("/api/teachers/delete/{id}", teacherId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Constants.REMOVED))
                .andExpect(jsonPath("$.message").value("****teacher deleted successfully****"))
                .andExpect(jsonPath("$.data").value("****"));

        verify(teachersService, times(1)).deleteById(teacherId);
    }

    @AfterAll
    public static void testFinish() {
        System.out.println("Teachers Controller Test case execution finished");
    }
}



