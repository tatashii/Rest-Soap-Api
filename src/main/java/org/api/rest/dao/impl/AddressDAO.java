package org.api.rest.dao.impl;

import javax.persistence.EntityManager;
import org.api.rest.entity.Address;
import org.api.rest.entity.Department;

public class AddressDAO {

    public Address findById(Integer addressId , EntityManager em ) {
        return em.find(Address.class, addressId);
    }
}
