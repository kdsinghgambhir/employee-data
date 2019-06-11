package empData.io.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import empData.io.entity.Employee;
import empData.io.repository.EmployeeRepository;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("application-integrationtest.properties")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repository;

    @Before
    public void setup()
    {
        Employee emp = new Employee();
        emp.setId("Karandeep-empid");
        emp.setFirstname("Karandeep");
        emp.setLastname("Singh");
        emp.setEmail("kdsgambhirs@gmail.com");
        repository.save(emp);
    }

    @After
    public void cleanup()
    {
        repository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void findOne() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/employees/Karandeep-empid"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("kdsgambhirs@gmail.com")));
    }

    @Test
    public void findOne404() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/employees/abcd-empid"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());
    }

    @Test
    public void create() throws Exception
    {

        ObjectMapper mapper = new ObjectMapper();

        Employee emp = new Employee();
        emp.setFirstname("Karan");
        emp.setLastname("Singh");
        emp.setEmail("gambhirs@gmail.com");

        mvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(emp))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}