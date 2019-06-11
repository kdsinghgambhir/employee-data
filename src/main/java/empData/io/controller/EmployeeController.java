package empData.io.controller;

import empData.io.entity.Employee;
import empData.io.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employees")
@Api(description = "Employee Related Endpoints")
public class EmployeeController
{
    @Autowired
    EmployeeService service;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find All employees",
            notes = "Returns a list of all employees available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Employee> findAll()
    {
        return service.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find employees by ID",
            notes = "Returns a single employee or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee findOne(@ApiParam(value = "Id of the Employee", required = true)
                                @PathVariable("id") String empid)
    {
        return service.findOne(empid);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee create(@RequestBody Employee employee)
    {
        return service.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee update(@PathVariable("id") String empid, @RequestBody Employee employee)
    {
        return service.update(empid, employee);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String empid)
    {

    }
}
