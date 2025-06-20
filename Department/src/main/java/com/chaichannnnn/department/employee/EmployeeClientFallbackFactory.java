package com.chaichannnnn.department.employee;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeClientFallbackFactory implements FallbackFactory<EmployeeClient> {

    @Override
    public EmployeeClient create(Throwable cause) {
        return new EmployeeClient() {

            @Override
            public List<Employee> findEmployeeByDepartmentId(String departmentId) {
                System.err.println("Fallback for findEmployeeByDepartmentId due to: " + cause);
                return List.of(); // หรือ return default employee list
            }

            @Override
            public List<Employee> testFindEmployeeByDepartmentId() {
                System.err.println("Fallback for testFindEmployeeByDepartmentId due to: " + cause);
                return List.of();
            }
        };
    }
}

//| สิ่งที่ต้องตรงกัน          | รายละเอียด      |
//| -------------------------- | --------------- |
//| ชื่อ method                | เหมือนกันเป๊ะ ๆ |
//| จำนวนและชนิดของ parameters | ตรงกันทั้งหมด   |
//| return type                | เหมือนกัน       |


