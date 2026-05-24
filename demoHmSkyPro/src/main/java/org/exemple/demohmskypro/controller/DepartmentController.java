package org.exemple.demohmskypro.controller;

import org.exemple.demohmskypro.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.exemple.demohmskypro.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeesByDepartment(@RequestParam int departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping("/all-by-department")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }
}