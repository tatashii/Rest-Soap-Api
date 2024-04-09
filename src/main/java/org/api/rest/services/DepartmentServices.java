package org.api.rest.services;

import org.api.rest.dao.impl.*;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.entity.Department;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.AddressMapper;
import org.api.rest.mapper.DepartmentMapper;
import org.api.rest.mapper.EmpoyeeMapper;
import org.api.rest.mapper.LeaveeMapper;

import java.util.List;

public class DepartmentServices {
    private DepartmentDAO departmentDAO;
    private DepartmentMapper departmentMapper;

    public DepartmentServices() {

        this.departmentDAO = new DepartmentDAO();
        this.departmentMapper = DepartmentMapper.INSTANCE;

    }

    public List<DepartmentDto> getAllDepartments() {
        try {
            return Database.doInTransaction(em -> {
                List<Department> departments = departmentDAO.getAll(em);
                return departmentMapper.toListDto(departments);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve departments from the database" + e);

            throw new RuntimeException("Failed to retrieve departments", e);
        }
    }

}
