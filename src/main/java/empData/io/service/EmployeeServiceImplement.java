package empData.io.service;

import empData.io.entity.Employee;
import empData.io.exception.BadRequestException;
import empData.io.exception.ResourceNotFoundException;
import empData.io.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements EmployeeService
{
    @Autowired
    private EmployeeRepository repository;

    @Transactional(readOnly = true)
    public List<Employee> findAll()
    {
        return (List<Employee>)repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findOne(String id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee with id = " + id + " NOT FOUND"));
    }

    @Transactional
    public Employee create(Employee emp)
    {
        Optional<Employee> employee = repository.findByEmail(emp.getEmail());
        if(employee.isPresent())
        {
            throw new BadRequestException("Employees with email = " +emp.getEmail() + " already exists");
        }
        else
        {
            return repository.save(emp);
        }
    }

    @Transactional
    public Employee update(String id, Employee emp)
    {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employees with ID = " +id + " does not exists")
                );
    }

    @Transactional
    public void delete(String id) {
        Optional<Employee> employee = repository.findById(id);
        if(!employee.isPresent())
        {
            throw new ResourceNotFoundException("Employees with ID = " +id + " does not exists");
        }
        repository.delete(employee.get());
    }
}
