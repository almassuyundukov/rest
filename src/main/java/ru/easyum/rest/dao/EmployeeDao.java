package ru.easyum.rest.dao;

import ru.easyum.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAllEmployee();
    Employee findEmployeeById(Long id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
}
