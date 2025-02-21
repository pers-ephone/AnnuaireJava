package com.amazingname.api.controller;

import com.amazingname.api.model.Employee;
import com.amazingname.api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/search/{search}")
    public List<Employee> searchEmployee(@PathVariable String search) {
        // general search
        List<Employee> result = employeeService.searchEmployeeByFirstName(search);
        List<Employee> lastNameSearch = employeeService.searchEmployeeByLastName(search);
        List<Employee> emailSearch = employeeService.searchEmployeeByEmail(search);
        result.addAll(lastNameSearch);
        result.addAll(emailSearch);
        // remove duplicates
        return new ArrayList<>(
                new HashSet<>(result)
        );
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
