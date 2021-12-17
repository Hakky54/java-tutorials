package nl.altindag.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.altindag.server.model.Employee;
import nl.altindag.server.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/employee")
    public List<Employee> getEmployees() {
        return service.getAll();
    }

    @PostMapping(value = "/api/employee")
    public void saveEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        service.save(employee);
    }

}
