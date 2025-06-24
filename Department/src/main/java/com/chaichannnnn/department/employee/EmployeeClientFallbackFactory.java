package com.chaichannnnn.department.employee;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeClientFallbackFactory implements FallbackFactory<EmployeeClient> {

    private final Tracer tracer;

    public EmployeeClientFallbackFactory(Tracer tracer) {
        this.tracer = tracer;
    }

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
                Span newSpan = tracer.nextSpan().name("fallback-testFindEmployeeByDepartmentId").start();
                try (Tracer.SpanInScope ws = tracer.withSpan(newSpan)) {
                    System.err.println("Fallback for testFindEmployeeByDepartmentId due to: " + cause);
                    return List.of();
                } finally {
                    newSpan.end();
                }
            }
        };
    }
}

//| สิ่งที่ต้องตรงกัน          | รายละเอียด      |
//| -------------------------- | --------------- |
//| ชื่อ method                | เหมือนกันเป๊ะ ๆ |
//| จำนวนและชนิดของ parameters | ตรงกันทั้งหมด   |
//| return type                | เหมือนกัน       |


