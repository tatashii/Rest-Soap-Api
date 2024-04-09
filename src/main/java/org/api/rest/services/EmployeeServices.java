package org.api.rest.services;

import org.api.rest.dao.impl.*;
import org.api.rest.dto.AddressDto;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.entity.*;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.AddressMapper;
import org.api.rest.mapper.EmpoyeeMapper;
//import org.api.rest.mapper.LeaveeMapper;
import org.api.rest.mapper.LeaveeMapper;
import org.mapstruct.Mapper;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServices {

    private EmployeeDAO employeeDAO;
    private EmpoyeeMapper empoyeeMapper;

    private AddressMapper addressMapper;
    private LeaveeMapper leaveeMapper;

    private DepartmentDAO departmentDAO;
    private AddressDAO addressDAO;
    private LeaveeDAO leaveeDAO;
    private RoleDAO roleDAO;

    public EmployeeServices() {
        this.employeeDAO = new EmployeeDAO();
        this.empoyeeMapper = EmpoyeeMapper.INSTANCE;
        this.leaveeMapper = LeaveeMapper.INSTANCE;
        this.addressMapper = AddressMapper.INSTANCE;
        this.departmentDAO = new DepartmentDAO();
        this.addressDAO = new AddressDAO();
        this.leaveeDAO = new LeaveeDAO();
        this.roleDAO = new RoleDAO();
    }

    public List<EmployeeDto> getAllEmployees() {
        try {
            return Database.doInTransaction(em -> {

                //returns list of employee entity that works with database
                //so we will use (mapper struct) to change it to employeeDto and use it in the app
                List<Employee> employees = employeeDAO.getAllEmployees(em);
                System.out.println("I'm here public  List<EmployeeDto> getAllEmployees() in EmployeeServices Class");
                System.out.println("EmpoyeeMapper ---> " + empoyeeMapper);
                return empoyeeMapper.toListDto(employees);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve employees from the database" + e);

            throw new RuntimeException("Failed to retrieve employees", e);
        }
    }

    public boolean updateEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = empoyeeMapper.toEntity(employeeDto);

            // Set Department if provided
            if (employeeDto.getDepartmentId() != null) {
                Department department = Database.doInTransaction(em -> {
                    return departmentDAO.findById(employeeDto.getDepartmentId(), em);
                });
                employee.setDepartment(department);
            }

            // Set Role if provided
            if (employeeDto.getId() != null) {
                Role role = Database.doInTransaction(em -> {
                    return roleDAO.findById(employeeDto.getId(), em);
                });
                employee.setRole(role);
            }

            // Update Addresses if provided
            if (employeeDto.getAddresses() != null && !employeeDto.getAddresses().isEmpty()) {
                for (AddressDto addressDto : employeeDto.getAddresses()) {
                    Address address = Database.doInTransaction(em -> {
                        return addressDAO.findById(addressDto.getId(), em);
                    });
                    if (address != null) {
                        // Update address properties based on the DTO
                        address.setHouseNo(addressDto.getHouseNo());
                        address.setStreet(addressDto.getStreet());
                        address.setCity(addressDto.getCity());
                        address.setPincode(addressDto.getPincode());
                        address.setState(addressDto.getState());
                    } else {
                        throw new IllegalArgumentException("Address with ID " + addressDto.getId() + " not found");
                    }
                }
            }

            // Update Leavees if provided
            if (employeeDto.getLeavees() != null && !employeeDto.getLeavees().isEmpty()) {
                for (LeaveeDto leaveeDto : employeeDto.getLeavees()) {
                    Leavee leavee = Database.doInTransaction(em -> {
                        return leaveeDAO.findById(leaveeDto.getId(), em);
                    });
                    if (leavee != null) {
                        // Update leavee properties based on the DTO
                        leavee.setReason(leaveeDto.getReason());
                        leavee.setStatus(leaveeDto.getStatus());
                        leavee.setLeaveStartDate(leaveeDto.getLeaveStartDate());
                        leavee.setLeaveEndDate(leaveeDto.getLeaveEndDate());
                    } else {
                        throw new IllegalArgumentException("Leavee with ID " + leaveeDto.getId() + " not found");
                    }
                }
            }

            // Now, update the employee entity itself
            Database.doInTransactionWithoutResult(em -> {
                employeeDAO.update(employee, em);
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addEmployee(EmployeeDto employeeDto) {

//        try{
        Employee employee = empoyeeMapper.toEntity(employeeDto);

        if (employeeDto.getDepartmentId() != null) {
            Department department = Database.doInTransaction(em -> {
                return departmentDAO.findById(employeeDto.getDepartmentId(), em);
            });
            employee.setDepartment(department);
        }

        // Set Role if provided
        if (employeeDto.getId() != null) {
            Role role = Database.doInTransaction(em -> {
                return roleDAO.findById(employeeDto.getId(), em);
            });
            employee.setRole(role);
        }

        if (employeeDto.getAddresses() != null && !employeeDto.getAddresses().isEmpty()) {
            Set<Address> addresses = new LinkedHashSet<>();
            Set<AddressDto> addressDto = employeeDto.getAddresses();
            addresses = addressMapper.toSetEntity(addressDto);
            employee.setAddresses(addresses);
        }

        // Set Leavees if provided
        if (employeeDto.getLeavees() != null && !employeeDto.getLeavees().isEmpty()) {
            Set<Leavee> leavees = new LinkedHashSet<>();
            Set<LeaveeDto> leaveeDto = employeeDto.getLeavees();
            leavees = leaveeMapper.toSetEntity(leaveeDto);
            employee.setLeavees(leavees);
        }

        Database.doInTransactionWithoutResult(em -> {
            employeeDAO.save(employee, em);
        });

        return true;
//    } catch (Exception e) {
//        e.printStackTrace();
//        return false;
//    }

  }

    public boolean deleteEmployee(EmployeeDto employeeDto) {

        try {
            Database.doInTransactionWithoutResult(em -> {
                Employee employee = empoyeeMapper.toEntity(employeeDto);
                employeeDAO.delete(employee, em);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


//--------------------------------------------------------------------------------------------------
