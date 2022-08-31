/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        LOGGER.info("saving employee to the database...\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee));
        employees.add(employee);
    }

}
