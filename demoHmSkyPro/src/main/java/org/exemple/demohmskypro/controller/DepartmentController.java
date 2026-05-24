package org.exemple.demohmskypro.controller;

import org.exemple.demohmskypro.model.Employee;
import org.springframework.web.bind.annotation.*;
import org.exemple.demohmskypro.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable int id) {
        return departmentService.getEmployeesByDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySum(@PathVariable int id) {
        return departmentService.getSalarySum(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalary(@PathVariable int id) {
        return departmentService.getMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalary(@PathVariable int id) {
        return departmentService.getMinSalary(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }
}