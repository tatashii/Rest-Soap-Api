package org.api.rest.dao.impl;

import javax.persistence.EntityManager;
import org.api.rest.entity.Department;
import org.api.rest.entity.Leavee;

public class LeaveeDAO {

    public Leavee findById(Integer leaveeId , EntityManager em ) {
        return em.find(Leavee.class, leaveeId);
    }
}
