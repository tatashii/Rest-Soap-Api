package org.api.rest.dao.impl;

import org.api.rest.dao.interfacee.RoleDAOInt;
import org.api.rest.entity.Address;
import org.api.rest.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleDAO implements RoleDAOInt {
    @Override
    public void save(Role role, EntityManager em) {
        em.persist(role);
    }

    @Override
    public void update(Role role, EntityManager em) {
        em.persist(role);
    }

    @Override
    public void delete(Role role, EntityManager em) {
        em.persist(role);
    }

    @Override
    public List<Role> getAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Role e", Role.class).getResultList();
    }

    public Role findByName(String roleName, EntityManager em) {
        return em.find(Role.class, roleName);
    }

    public Role findById(Integer id, EntityManager em) {
        return em.find(Role.class, id);
    }
}
