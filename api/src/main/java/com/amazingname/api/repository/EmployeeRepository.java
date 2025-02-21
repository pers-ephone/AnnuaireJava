package com.amazingname.api.repository;

import com.amazingname.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByEmailContaining(String email);
    List<Employee> findAllByFirstNameContaining(String firstName);
    List<Employee> findAllByLastNameContaining(String lastName);
}
