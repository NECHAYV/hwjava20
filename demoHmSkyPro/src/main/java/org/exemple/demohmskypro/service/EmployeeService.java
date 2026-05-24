package org.exemple.demohmskypro.service;
import org.exemple.demohmskypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    //Тестовые данные
    public EmployeeService() {
        employees.add(new Employee("Иванов Иван Иванович", 1, 100));
        employees.add(new Employee("Петров Петр Петрович", 2, 200));
        employees.add(new Employee("Сидоров Сидор Сидорович", 1, 300));
        employees.add(new Employee("Козлов Алексей Сергеевич", 3, 150));
        employees.add(new Employee("Морозова Анна Игоревна", 2, 400));
        employees.add(new Employee("Волков Дмитрий Андреевич", 4, 80));
        employees.add(new Employee("Зайцева Елена Викторовна", 5, 250));
        employees.add(new Employee("Медведев Игорь Павлович", 3, 350));
        employees.add(new Employee("Лисицына Ольга Дмитриевна", 4, 170));
        employees.add(new Employee("Соколов Никита Романович", 5, 420));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Employee addEmployee(String fullName, int department, int salary) {
        Employee employee = new Employee(fullName, department, salary);
        employees.add(employee);
        return employee;
    }

    public Employee getEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public double calculateAverageSalary() {
        return employees.stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> calculateTaxes(String taxType) {
        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::getFullName,
                        e -> calculateTax(e.getSalary(), taxType)
                ));
    }

    private double calculateTax(int salary, String taxType) {
        switch (taxType) {
            case "PROPORTIONAL":
                return salary * 0.13;
            case "PROGRESSIVE":
                if (salary <= 150) {
                    return salary * 0.13;
                } else if (salary <= 350) {
                    return salary * 0.17;
                } else {
                    return salary * 0.21;
                }
            default:
                return 0;
        }
    }

    public boolean containsEmployee(Employee employee) {
        return employees.stream()
                .anyMatch(e -> e.equals(employee));
    }
}
