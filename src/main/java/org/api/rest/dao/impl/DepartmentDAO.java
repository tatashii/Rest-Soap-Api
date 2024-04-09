package org.api.rest.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;

import org.api.rest.dao.interfacee.DepartmentDAOInt;
import org.api.rest.dao.interfacee.ParentDAO;
import org.api.rest.entity.Department;
import org.api.rest.entity.Employee;
import org.hibernate.annotations.Parent;

import java.util.List;

public class DepartmentDAO implements DepartmentDAOInt {
    public Department findById(Integer departmentId , EntityManager em ) {
        return em.find(Department.class, departmentId);
    }


    @Override
    public void save(Department department, EntityManager em) {
        em.persist(department);
    }

    @Override
    public void update(Department department, EntityManager em) {
        em.merge(department);
    }

    @Override
    public void delete(Department department, EntityManager em) {
        em.remove(department);
    }

    @Override
    public List<Department> getAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Department e", Department.class).getResultList();
    }
}
