package org.api.rest.dao.impl;

import javax.persistence.EntityManager;
import org.api.rest.entity.Department;

public class DepartmentDAO {
    public Department findById(Integer departmentId , EntityManager em ) {
        return em.find(Department.class, departmentId);
    }


}
