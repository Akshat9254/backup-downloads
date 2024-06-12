package com.spring.testing.service;

import com.spring.testing.exception.ResourceAlreadyExistsException;
import com.spring.testing.model.Employee;
import com.spring.testing.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee save(@NonNull final Employee employee) {

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("employee", "email", employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
