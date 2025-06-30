package com.chaichannnnn.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

//    private final EmployeeService employeeService;


//    @GetMapping
//    public List<Employee> findAllEmployee() {
//        return employeeService.findAllEmployee();
//    }
//
//    @GetMapping("/{id}")
//    public Employee findEmployee(@PathVariable String id) {
//        return employeeService.findEmployeeById(id);
//    }
//
//    @PostMapping
//    public Employee addEmployee(@RequestBody Employee employee) {
//        return employeeService.addNewEmployee(employee);
//    }
//
//    @PutMapping("/{id}")
//    public Employee editEmployee(@PathVariable String id, @RequestBody Employee employee) {
//        return employeeService.updateEmployeeById(id, employee);
//    }
//
//    @DeleteMapping
//    public void deleteEmployee(@PathVariable String id) {
//        employeeService.deleteEmployeeById(id);
//    }
//
//    @GetMapping("/department/{departmentId}")
//    public List<Employee> findEmployeeByDepartmentId(@PathVariable String departmentId) {
//        return employeeService.findEmployeeByDepartmentId(departmentId);
//    }
//
//    @GetMapping("/organization/{organizationId}")
//    public List<Employee> findEmployeeByOrganizationId(@PathVariable String organizationId) {
//        return employeeService.findEmployeeByOrganizationId(organizationId);
//    }

    @GetMapping("/test")
    public Employee testEmployee() {
//        Employee employee = new Employee();
//        employee.setId("id1");
//        employee.setAge(20);
//        employee.setName("hello employee");
//        return employee;
        return Employee.builder().id("1").age(50).name("hello employee").build();
    }

    @GetMapping("/tests")
    public List<Employee> testEmployeeList() {
        return List.of(Employee.builder().id("1").age(50).name("hello employee").build());
    }
}
