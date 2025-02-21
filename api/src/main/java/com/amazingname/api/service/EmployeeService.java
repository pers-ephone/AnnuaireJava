package com.amazingname.api.service;

import com.amazingname.api.model.Employee;
import com.amazingname.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployeeByEmail(String search) {
        return employeeRepository.findAllByEmailContaining(search);
    }
    public List<Employee> searchEmployeeByFirstName(String search) {
        return employeeRepository.findAllByFirstNameContaining(search);
    }
    public List<Employee> searchEmployeeByLastName(String search) {
        return employeeRepository.findAllByLastNameContaining(search);
    }
}
