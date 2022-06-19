package ru.easyum.rest.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.easyum.rest.entity.Employee;
import ru.easyum.rest.exception.EmployeeNotFoundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

//    public EmployeeDaoImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public List<Employee> findAllEmployee() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(" from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee deleteEmployee = findEmployeeById(id);
        if(deleteEmployee == null) throw new EmployeeNotFoundException("Employee with id" + id + "is not found");
        session.delete(deleteEmployee);

    }
}
