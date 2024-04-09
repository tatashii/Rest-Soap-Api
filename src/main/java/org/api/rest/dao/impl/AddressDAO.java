package org.api.rest.dao.impl;

import javax.persistence.EntityManager;

import org.api.rest.dao.interfacee.AddressDAOInt;
import org.api.rest.dao.interfacee.ParentDAO;
import org.api.rest.entity.Address;
import org.api.rest.entity.Department;

import java.util.List;

public class AddressDAO implements AddressDAOInt {

    public Address findById(Integer addressId , EntityManager em ) {
        return em.find(Address.class, addressId);
    }


    @Override
    public void save(Address address, EntityManager em) {
        em.persist(address);
    }

    @Override
    public void update(Address address, EntityManager em) {
        em.merge(address);
    }

    @Override
    public void delete(Address address, EntityManager em) {
        em.remove(address);
    }

    @Override
    public List<Address> getAll(EntityManager em) {
        return em.createQuery("SELECT e FROM Address e", Address.class).getResultList();
    }
}
