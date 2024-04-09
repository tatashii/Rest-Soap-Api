package org.api.rest.services;

import org.api.rest.dao.impl.RoleDAO;
import org.api.rest.dto.RoleDto;
import org.api.rest.entity.Role;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.RoleMapper;
import org.api.rest.mapper.RoleMapper;

import java.util.List;

public class RoleServices {

    private RoleDAO roleDAO;
    private RoleMapper roleMapper;

    public RoleServices() {

        this.roleDAO = new RoleDAO();
        this.roleMapper = RoleMapper.INSTANCE;

    }

    public List<RoleDto> getAllRoles() {
        try {
            return Database.doInTransaction(em -> {
                List<Role> rolees = roleDAO.getAll(em);
                return roleMapper.toListDto(rolees);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve rolees from the database" + e);

            throw new RuntimeException("Failed to retrieve rolees", e);
        }
    }

    public boolean addRole(RoleDto roleDto) {
        try {
            Role role = roleMapper.toEntity(roleDto);

            Database.doInTransactionWithoutResult(em -> {
                roleDAO.save(role, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing role
    public boolean updateRole(RoleDto roleDto) {
        try {
            Role existingRole = Database.doInTransaction(em -> {
                return roleDAO.findByName(roleDto.getName(), em);
            });

            // Check if the role exists
            if (existingRole == null) {
                throw new IllegalArgumentException("Role with ID " + roleDto.getId() + " not found");
            }

            // Update role properties
            existingRole.setName(Role.RoleName.DEVELOPER);

            // Save the updated role
            Database.doInTransactionWithoutResult(em -> {
                roleDAO.update(existingRole, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an existing role
    public boolean deleteRole(int roleId) {
        try {
            Role roleToDelete = Database.doInTransaction(em -> {
                return roleDAO.findById(roleId, em);
            });

            // Check if the role exists
            if (roleToDelete == null) {
                throw new IllegalArgumentException("Role with ID " + roleId + " not found");
            }

            // Delete the role
            Database.doInTransactionWithoutResult(em -> {
                roleDAO.delete(roleToDelete, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}