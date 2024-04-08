package org.api.rest.dao.impl;

import org.api.rest.dao.interfacee.EmployeeDAOInt;
import org.api.rest.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDAO implements EmployeeDAOInt {
    @Override
    public void add(Employee entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Employee dtoModel) {

    }

    @Override
    public Employee get(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    //------------------------------------------------------------------------------------------------------
    public List<Employee> getAllEmployees(EntityManager em) {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }
}
