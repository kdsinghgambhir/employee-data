package empData.io.service;

import empData.io.entity.Employee;
import empData.io.exception.ResourceNotFoundException;
import empData.io.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
public class EmployeeServiceImplementTest {

    @TestConfiguration
    static class EmployeeServiceImplementTestConfiguration
    {
        @Bean
        public EmployeeService getService()
        {
            return new EmployeeServiceImplement();
        }
    }

    @Autowired
    private EmployeeService service;

    @MockBean
    private EmployeeRepository repository;

    private List<Employee> employees;

    @Before
    public void setup()
    {
        Employee emp = new Employee();
        emp.setFirstname("Karandeep");
        emp.setLastname("Singh");
        emp.setEmail("kdsgambhirs@gmail.com");

        employees = Collections.singletonList(emp);

        Mockito.when(repository.findAll())
                .thenReturn(employees);
        Mockito.when(repository.findById(emp.getId()))
                .thenReturn(Optional.of(emp));
    }

    @After
    public void cleanup()
    {

    }

    @Test
    public void findAll() throws Exception {
        List<Employee> result = service.findAll();
        Assert.assertEquals("Employee List Should Match", employees, result);
    }

    @Test
    public void findOne() throws Exception{
        Employee result = service.findOne(employees.
                get(0).
                getId());
        Assert.assertEquals("Employee Should Match", employees.get(0), result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOneNotFound() throws Exception{
        Employee result = service.findOne("abcd");
        Assert.assertEquals("Employee Should not Match", employees.get(0), result);
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}