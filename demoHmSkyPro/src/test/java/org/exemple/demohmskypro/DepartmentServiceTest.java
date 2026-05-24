package org.exemple.demohmskypro;

import org.exemple.demohmskypro.model.Employee;
import org.exemple.demohmskypro.service.DepartmentService;
import org.exemple.demohmskypro.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> testEmployees;

    @BeforeEach
    void setUp() {
        testEmployees = Arrays.asList(
                new Employee("Иванов", 1, 100),
                new Employee("Петров", 2, 200),
                new Employee("Сидоров", 1, 300),
                new Employee("Козлов", 3, 150)
        );
        when(employeeService.getAllEmployees()).thenReturn(testEmployees);
    }

    @Test
    void shouldReturnEmployeesByDepartment() {
        List<Employee> dept1 = departmentService.getEmployeesByDepartment(1);
        Assertions.assertEquals(2, dept1.size());
        assertTrue(dept1.stream().allMatch(e -> e.getDepartment() == 1));
    }

    @Test
    void shouldReturnEmptyListForUnknownDepartment() {
        List<Employee> dept5 = departmentService.getEmployeesByDepartment(5);
        assertTrue(dept5.isEmpty());
    }

    @Test
    void shouldReturnSalarySum() {
        int sum = departmentService.getSalarySum(1);
        Assertions.assertEquals(400, sum);
    }

    @Test
    void shouldReturnZeroSumForEmptyDepartment() {
        int sum = departmentService.getSalarySum(5);
        Assertions.assertEquals(0, sum);
    }

    @Test
    void shouldReturnMaxSalary() {
        int max = departmentService.getMaxSalary(1);
        Assertions.assertEquals(300, max);
    }

    @Test
    void shouldReturnZeroMaxForEmptyDepartment() {
        int max = departmentService.getMaxSalary(5);
        Assertions.assertEquals(0, max);
    }

    @Test
    void shouldReturnMinSalary() {
        int min = departmentService.getMinSalary(1);
        Assertions.assertEquals(100, min);
    }

    @Test
    void shouldReturnZeroMinForEmptyDepartment() {
        int min = departmentService.getMinSalary(5);
        Assertions.assertEquals(0, min);
    }

    @Test
    void shouldGroupEmployeesByDepartment() {
        Map<Integer, List<Employee>> grouped = departmentService.getAllEmployeesGroupedByDepartment();
        Assertions.assertEquals(3, grouped.size());
        assertTrue(grouped.containsKey(1));
        assertTrue(grouped.containsKey(2));
        assertTrue(grouped.containsKey(3));
        Assertions.assertEquals(2, grouped.get(1).size());
    }

    @Test
    void shouldHandleEmptyEmployeeList() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());
        assertTrue(departmentService.getEmployeesByDepartment(1).isEmpty());
        Assertions.assertEquals(0, departmentService.getSalarySum(1));
        Assertions.assertEquals(0, departmentService.getMaxSalary(1));
        Assertions.assertEquals(0, departmentService.getMinSalary(1));
        assertTrue(departmentService.getAllEmployeesGroupedByDepartment().isEmpty());
    }
}