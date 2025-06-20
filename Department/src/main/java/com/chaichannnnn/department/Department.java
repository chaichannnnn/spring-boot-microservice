package com.chaichannnnn.department;

import com.chaichannnnn.department.employee.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
    private String id;
    private String name;
    private String organizationId;
    private List<Employee> employees;
}