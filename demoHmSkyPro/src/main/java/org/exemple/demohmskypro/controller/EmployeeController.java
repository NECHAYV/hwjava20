package org.exemple.demohmskypro.controller;

import org.exemple.demohmskypro.model.Employee;
import org.springframework.web.bind.annotation.*;
import org.exemple.demohmskypro.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestParam String fullName,
                                @RequestParam int department,
                                @RequestParam int salary) {
        return employeeService.addEmployee(fullName, department, salary);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/average-salary")
    public double getAverageSalary() {
        return employeeService.calculateAverageSalary();
    }

    @GetMapping("/taxes")
    public Map<String, Double> calculateTaxes(@RequestParam String taxType) {
        return employeeService.calculateTaxes(taxType);
    }
}
