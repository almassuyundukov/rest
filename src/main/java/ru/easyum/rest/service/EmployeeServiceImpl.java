package ru.easyum.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.easyum.rest.dao.EmployeeDao;
import ru.easyum.rest.entity.Employee;
import ru.easyum.rest.exception.EmployeeNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    @Transactional
    public List<Employee> findAllEmployee() {
        return employeeDao.findAllEmployee();
    }

    @Override
    @Transactional
    public Employee findEmployeeById(Long id) {
        Employee employee = employeeDao.findEmployeeById(id);
        if (employee == null)
            throw new EmployeeNotFoundException("Employee with id " + id + " not found in database");
        return employee;
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee, Long id) {
        Employee employee1 = employeeDao.findEmployeeById(id);
        employee1.setDepartment(employee.getDepartment());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setSalary(employee.getSalary());
        employeeDao.updateEmployee(employee1);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        employeeDao.deleteEmployeeById(id);
    }
}
