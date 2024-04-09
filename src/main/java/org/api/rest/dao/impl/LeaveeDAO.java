package org.api.rest.dao.impl;

import javax.persistence.EntityManager;

import org.api.rest.dao.interfacee.LeaveeDAOInt;
import org.api.rest.dao.interfacee.ParentDAO;
import org.api.rest.entity.Department;
import org.api.rest.entity.Leavee;

import java.util.List;

public class LeaveeDAO implements LeaveeDAOInt {

    public Leavee findById(Integer leaveeId , EntityManager em ) {
        return em.find(Leavee.class, leaveeId);
    }

    @Override
    public void save(Leavee leavee, EntityManager em) {
        em.persist(leavee);
    }

    @Override
    public void update(Leavee leavee, EntityManager em) {
        em.merge(leavee);
    }

    @Override
    public void delete(Leavee leavee, EntityManager em) {
        em.remove(leavee);
    }

    @Override
    public List<Leavee> getAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Leavee e", Leavee.class).getResultList();
    }
}
