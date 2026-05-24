package org.exemple.demohmskypro.service;

import org.exemple.demohmskypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public int getSalarySum(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalary(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
    }

    public int getMinSalary(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .min()
                .orElse(0);
    }

    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}