package com.chaichannnnn.department;

import com.chaichannnnn.department.employee.Employee;
import com.chaichannnnn.department.employee.EmployeeClient;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private static final String SAMPLE_SERVICE = "sampleService";

    private final EmployeeClient employeeClient;

    //@CircuitBreaker(name = SAMPLE_SERVICE, fallbackMethod = "fallbackResponse")
    public Department testFindDepartment() {
        Optional<Department> department = Optional.of(Department.builder().id("123").name("hello department").build());
        department.ifPresent(d -> {
            List<Employee> employees = employeeClient.testFindEmployeeByDepartmentId();
            d.setEmployees(employees);
        });

        return department.orElse(null);
    }

}
