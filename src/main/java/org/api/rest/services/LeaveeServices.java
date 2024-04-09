package org.api.rest.services;

import org.api.rest.dao.impl.EmployeeDAO;
import org.api.rest.dao.impl.LeaveeDAO;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.entity.Leavee;
import org.api.rest.entity.Employee;
import org.api.rest.entity.Leavee;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.LeaveeMapper;
import org.api.rest.mapper.LeaveeMapper;

import java.util.List;

public class LeaveeServices {

    private LeaveeDAO leaveeDAO;
    private LeaveeMapper leaveeMapper;
    private EmployeeDAO employeeDAO;

    public LeaveeServices() {

        this.leaveeDAO = new LeaveeDAO();
        this.leaveeMapper = LeaveeMapper.INSTANCE;
        this.employeeDAO = new EmployeeDAO();
    }

    public List<LeaveeDto> getAllLeavees() {
        try {
            return Database.doInTransaction(em -> {
                List<Leavee> leavees = leaveeDAO.getAll(em);
                return leaveeMapper.toListDto(leavees);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve leavees from the database" + e);

            throw new RuntimeException("Failed to retrieve leavees", e);
        }
    }


    public boolean addLeavee(LeaveeDto leaveeDto) {
        try {
            Leavee leavee = leaveeMapper.toEntity(leaveeDto);

            // Fetch the employee for the provided leavee
            Employee employee = Database.doInTransaction(em -> {
                return employeeDAO.findById(leaveeDto.getEmployeeId(), em);
            });
            if (employee == null) {
                throw new IllegalArgumentException("Employee with ID " + leaveeDto.getEmployeeId() + " not found");
            }

            leavee.setEmployee(employee);

            Database.doInTransactionWithoutResult(em -> {
                leaveeDAO.save(leavee, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing leavee
    public boolean updateLeavee(LeaveeDto leaveeDto) {
        try {
            Leavee existingLeavee = Database.doInTransaction(em -> {
                return leaveeDAO.findById(leaveeDto.getId(), em);
            });

            // Check if the leavee exists
            if (existingLeavee == null) {
                throw new IllegalArgumentException("Leavee with ID " + leaveeDto.getId() + " not found");
            }

            // Update leavee properties
            existingLeavee.setStatus(leaveeDto.getStatus());
            existingLeavee.setReason(leaveeDto.getReason());
            existingLeavee.setLeaveStartDate(leaveeDto.getLeaveStartDate());
            existingLeavee.setLeaveEndDate(leaveeDto.getLeaveEndDate());

            // Save the updated leavee
            Database.doInTransactionWithoutResult(em -> {
                leaveeDAO.update(existingLeavee, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an existing leavee
    public boolean deleteLeavee(int leaveeId) {
        try {
            Leavee leaveeToDelete = Database.doInTransaction(em -> {
                return leaveeDAO.findById(leaveeId, em);
            });

            // Check if the leavee exists
            if (leaveeToDelete == null) {
                throw new IllegalArgumentException("Leavee with ID " + leaveeId + " not found");
            }

            // Delete the leavee
            Database.doInTransactionWithoutResult(em -> {
                leaveeDAO.delete(leaveeToDelete, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}