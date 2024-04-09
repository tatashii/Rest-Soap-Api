package org.api.rest.services;

import org.api.rest.dao.impl.*;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.entity.Department;
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

    public boolean deleteDepartment(DepartmentDto departmentDto) {

        try {
            Database.doInTransactionWithoutResult(em -> {
                Department department = departmentMapper.toEntity(departmentDto);
                departmentDAO.delete(department, em);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDepartment(DepartmentDto departmentDto) {
        try {
            Department department = departmentMapper.toEntity(departmentDto);

            // Validate if department exists
            Department existingDepartment = Database.doInTransaction(em -> {
                return departmentDAO.findById(departmentDto.getId(), em);
            });
            if (existingDepartment == null) {
                throw new IllegalArgumentException("Department with ID " + departmentDto.getId() + " not found");
            }

            // Update department properties
            existingDepartment.setName(department.getName());

            // Save the updated department
            Database.doInTransactionWithoutResult(em -> {
                departmentDAO.update(existingDepartment, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addDepartment(DepartmentDto departmentDto) {
//        try {
            Department department = departmentMapper.toEntity(departmentDto);
            Database.doInTransactionWithoutResult(em -> {
                departmentDAO.save(department, em);
//            });
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
        });
        return false;
    }




}
