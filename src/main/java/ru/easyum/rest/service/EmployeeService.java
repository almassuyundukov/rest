package ru.easyum.rest.service;

import ru.easyum.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();
    Employee findEmployeeById(Long id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee, Long id);
    void deleteEmployeeById(Long id);
}
