package com.chaichannnnn.department.employee;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employee {
    private String id;
    private String name;
    private int age;
    private String position;
    private String organizationId;
    private String departmentId;
}
