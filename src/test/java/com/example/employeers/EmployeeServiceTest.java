package com.example.employeers;

import com.example.employeers.exception.InvalidInputException;
import com.example.employeers.model.Employee;
import com.example.employeers.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {


    @Test
    void findEmployee_existing_returnsEmployee() {
        EmployeeService service = new EmployeeService();
        Employee emp = service.findEmployee("Kentucky", "Fried");
        assertNotNull(emp);
        assertEquals("Kentucky", emp.getFirstName());
        assertEquals(5000, emp.getSalary());
    }


    @Test
    void removeEmployee_existing_success() {
        EmployeeService service = new EmployeeService();
        int initialSize = service.getEmployees().size();

        boolean removed = service.removeEmployee("Kentucky", "Fried");
        assertTrue(removed);
        assertEquals(initialSize - 1, service.getEmployees().size());
    }

    @Test
    void removeEmployee_nonExisting_returnsFalse() {
        EmployeeService service = new EmployeeService();
        int initialSize = service.getEmployees().size();

        boolean removed = service.removeEmployee("Фейк", "Имя");
        assertFalse(removed);
        assertEquals(initialSize, service.getEmployees().size());
    }

    @Test
    void addEmployee_fullCapacity_returnsFalse() {
        EmployeeService service = new EmployeeService();
        boolean added = service.addEmployee("Test", "User", 1, 1000);
        assertFalse(added);
    }

    @Test
    void addEmployee_afterRemove_success() {
        EmployeeService service = new EmployeeService();
        service.removeEmployee("Kentucky", "Fried");

        boolean added = service.addEmployee("Иван", "Иванов", 1, 50000);
        assertTrue(added);

        Employee newEmp = service.findEmployee("Иван", "Иванов");
        assertNotNull(newEmp);
        assertEquals(50000, newEmp.getSalary());
    }

    @Test
    void validateInput_throwsOnNumbers() {
        EmployeeService service = new EmployeeService();
        assertThrows(InvalidInputException.class,
                () -> service.addEmployee("123", "Valid", 1, 1000));
    }
}
