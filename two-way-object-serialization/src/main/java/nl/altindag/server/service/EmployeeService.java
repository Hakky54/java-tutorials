package nl.altindag.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.altindag.server.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final ObjectMapper objectMapper;
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }

    public void save(Employee employee) throws JsonProcessingException {
        LOGGER.info("saving employee to the database..." + objectMapper.writeValueAsString(employee));
        employees.add(employee);
    }

}
