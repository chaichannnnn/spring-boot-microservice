package com.chaichannnnn.department.employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE",fallbackFactory = EmployeeClientFallbackFactory.class)
public interface EmployeeClient {

    @GetMapping("/employees/department/{departmentId}")
    List<Employee> findEmployeeByDepartmentId(@PathVariable String departmentId);

    @GetMapping("/employees/tests")
    List<Employee> testFindEmployeeByDepartmentId();
}
