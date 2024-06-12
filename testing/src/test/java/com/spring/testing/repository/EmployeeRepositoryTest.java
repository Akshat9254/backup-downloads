package com.spring.testing.repository;

import com.spring.testing.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("JUnit Test for save employee operation")
    void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given -> precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .build();

        // when -> action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then -> verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isPositive();
    }

    @Test
    @DisplayName("JUnit Test for find all employees operation")
    void givenEmployeesList_whenFindAll_thenReturnSavedEmployeesList() {
        // given -> precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@test.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when -> action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then -> verify the output
        assertThat(employeeList).isNotNull().hasSize(2);
    }

    @Test
    @DisplayName("JUnit Test for find employee by id operation")
    void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given -> precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .build();
        employeeRepository.save(employee);

        // when -> action or the behaviour that we are going to test
        Optional<Employee> employeeDB = employeeRepository.findById(employee.getId());

        // then -> verify the output
        assertThat(employeeDB).isPresent();
    }

    @Test
    @DisplayName("JUnit Test for find employee by email operation")
    void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
        // given -> precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .build();
        employeeRepository.save(employee);

        // when -> action or the behaviour that we are going to test
        Optional<Employee> employeeDB = employeeRepository.findByEmail(employee.getEmail());

        // then -> verify the output
        assertThat(employeeDB).isPresent();
    }
}