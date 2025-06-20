package com.chaichannnnn.department;

import com.chaichannnnn.department.employee.Employee;
import com.chaichannnnn.department.employee.EmployeeClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private static final String SAMPLE_SERVICE = "sampleService";

//    private final DepartmentRepository departmentRepository;
//
    private final EmployeeClient employeeClient;

//
//    public List<Department> findAllDepartment() {
//        return departmentRepository.findAll();
//    }
//
//    //@HystrixCommand(fallbackMethod = "findDepartmentByIdRecovery")
//    public Department findDepartmentById(String id) {
//        Optional<Department> department = departmentRepository.findById(id);
//        department.ifPresent(d -> {
//            List<Employee> employees = employeeClient.findEmployeeByDepartmentId(d.getId());
//            d.setEmployees(employees);
//        });
//
//        return department.orElse(null);
//    }
//
//    public Department findDepartmentByIdRecovery(String id) {
//        return departmentRepository.findById(id).orElse(null);
//    }
//
//    public Department addNewDepartment(Department department) {
//        return departmentRepository.save(department);
//    }
//
//    public Department updateDepartment(String departmentId, Department department) {
//        departmentRepository.findById(departmentId).ifPresent(d -> department.setId(d.getId()));
//        return departmentRepository.save(department);
//    }
//
//    public void deleteDepartment(String departmentId) {
//        departmentRepository.deleteById(departmentId);
//    }
//
//    public List<Department> findDepartmentByOrganizationId(String organizationId) {
//        return departmentRepository.findByOrganizationId(organizationId);
//    }

    @CircuitBreaker(name = SAMPLE_SERVICE, fallbackMethod = "fallbackResponse")
    public Department testFindDepartment() {
        Optional<Department> department = Optional.of(Department.builder().id("123").name("hello department").build());
        department.ifPresent(d -> {
            List<Employee> employees = employeeClient.testFindEmployeeByDepartmentId();
            d.setEmployees(employees);
        });

        return department.orElse(null);
    }

}
