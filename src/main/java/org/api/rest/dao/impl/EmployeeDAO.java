package org.api.rest.dao.impl;

import javax.persistence.EntityManager;
import org.api.rest.dao.interfacee.EmployeeDAOInt;
import org.api.rest.entity.Employee;


import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements EmployeeDAOInt {

    @Override
    public void save(Employee employee, javax.persistence.EntityManager em) {

        em.persist(employee);
    }

    @Override
    public void update(Employee employee, javax.persistence.EntityManager em) {

        em.merge(employee);
    }

    @Override
    public void delete(Employee employee, javax.persistence.EntityManager em) {

        em.remove(employee);
    }


    //------------------------------------------------------------------------------------------------------
    public List<Employee> getAllEmployees(EntityManager em) {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }
    //------------------------------------------------------------------------------------------------------


    @Override
    public Employee get(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Optional<Employee> get(Integer id, javax.persistence.EntityManager em) {
        return Optional.empty();
    }
}
