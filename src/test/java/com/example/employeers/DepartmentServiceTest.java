package com.example.employeers;

import com.example.employeers.model.Employee;
import com.example.employeers.service.DepartmentService;
import com.example.employeers.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Map<String, Employee> realEmployees = Map.of(
                "Kentucky Fried", new Employee("Kentucky", "Fried", 1, 5000),
                "Bugha", new Employee("Kyle", "Bugha", 2, 60000),
                "Mongraal", new Employee("Kyle", "Mongraal", 3, 550000),
                "Ninja", new Employee("Tyler", "Ninja", 4, 450000),
                "Tfue", new Employee("Turner", "Tfue", 5, 775000),
                "Clix", new Employee("Cody", "Clix", 6, 30000),
                "MrSavage", new Employee("Martin", "MrSavage", 7, 500000),
                "Mitro", new Employee("Diman", "Mitro", 8, 900000),
                "Benjyfishy", new Employee("Davidka", "Benjyfishy", 9, 800000),
                "Dakotaz", new Employee("Brett", "Dakotaz", 10, 85000)
        );
        when(employeeService.getEmployees()).thenReturn(realEmployees);
    }

    @Test
    public void getMinSalaryEmployeeDeparment1ReturnMinSalary() {
        Employee emp = departmentService.getMinSalaryEmployee(1);

        assertNotNull(emp);
        assertEquals("Kentucky", emp.getFirstName());
        assertEquals(5000, emp.getSalary());
    }

    @Test
    public void getMaxSalaryEmployeeDeparment1ReturnMaxSalary() {
        Employee emp = departmentService.getMaxSalaryEmployee(1);
        assertNotNull(emp);
        assertEquals("Kentucky", emp.getFirstName()); // Единственный сотрудник в отделе 1
        assertEquals(5000, emp.getSalary());
    }

}
