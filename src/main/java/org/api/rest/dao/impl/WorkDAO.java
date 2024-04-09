package org.api.rest.dao.impl;

import org.api.rest.dao.interfacee.LeaveeDAOInt;
import org.api.rest.dao.interfacee.ParentDAO;
import org.api.rest.dao.interfacee.WorkDAOInt;
import org.api.rest.entity.Department;
import org.api.rest.entity.Work;

import javax.persistence.EntityManager;
import java.util.List;

public class WorkDAO implements WorkDAOInt {
    @Override
    public void save(Work work, EntityManager em) {
        em.persist(work);
    }

    @Override
    public void update(Work work, EntityManager em) {

        em.merge(work);
    }

    @Override
    public void delete(Work work, EntityManager em) {

        em.remove(work);
    }

    @Override
    public List<Work> getAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Work e", Work.class).getResultList();
    }
}
