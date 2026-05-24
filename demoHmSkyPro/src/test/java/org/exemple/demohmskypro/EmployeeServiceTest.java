package org.exemple.demohmskypro;

import org.exemple.demohmskypro.model.Employee;
import org.exemple.demohmskypro.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
        employeeService.addEmployee("Иванов Иван", 1, 100);
        employeeService.addEmployee("Петров Петр", 2, 200);
        employeeService.addEmployee("Сидоров Сидор", 1, 300);
    }

    @Test
    void shouldReturnAllEmployees() {
        List<Employee> all = employeeService.getAllEmployees();
        Assertions.assertEquals(3, all.size());
    }

    @Test
    void shouldAddEmployee() {
        Employee newEmp = employeeService.addEmployee("Новый Сотрудник", 3, 500);
        assertNotNull(newEmp);
        Assertions.assertEquals(4, employeeService.getAllEmployees().size());
    }

    @Test
    void shouldRemoveEmployee() {
        Employee emp = employeeService.getAllEmployees().get(0);
        Employee removed = employeeService.removeEmployee(emp.getId());
        assertNotNull(removed);
        Assertions.assertEquals(2, employeeService.getAllEmployees().size());
    }

    @Test
    void shouldReturnNullWhenRemovingNonExistent() {
        Employee removed = employeeService.removeEmployee(999);
        assertNull(removed);
    }

    @Test
    void shouldFindEmployeeById() {
        Employee emp = employeeService.getAllEmployees().get(1);
        Employee found = employeeService.findEmployeeById(emp.getId());
        assertNotNull(found);
        Assertions.assertEquals(emp.getFullName(), found.getFullName());
    }

    @Test
    void shouldReturnNullForMissingId() {
        Employee found = employeeService.findEmployeeById(999);
        assertNull(found);
    }

    @Test
    void shouldCalculateAverageSalary() {
        double avg = employeeService.calculateAverageSalary();
        Assertions.assertEquals(200.0, avg, 0.01);
    }

    @Test
    void shouldHandleEmptyListForAverage() {
        EmployeeService emptyService = new EmployeeService();
        Assertions.assertEquals(0.0, emptyService.calculateAverageSalary());
    }

    @Test
    void shouldReturnTrueForExistingEmployeeBySalary() {
        Employee emp = new Employee("Тест", 1, 200);
        assertTrue(employeeService.containsEmployee(emp));
    }

    @Test
    void shouldReturnFalseForNonExistingSalary() {
        Employee emp = new Employee("Тест", 1, 999);
        assertFalse(employeeService.containsEmployee(emp));
    }
}

