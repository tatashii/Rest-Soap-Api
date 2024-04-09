package org.api.rest.services;

import org.api.rest.dao.impl.AddressDAO;
import org.api.rest.dao.impl.DepartmentDAO;
import org.api.rest.dao.impl.EmployeeDAO;
import org.api.rest.dao.impl.LeaveeDAO;
import org.api.rest.dto.AddressDto;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Department;
import org.api.rest.entity.Employee;
import org.api.rest.entity.Leavee;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.AddressMapper;
import org.api.rest.mapper.EmpoyeeMapper;
//import org.api.rest.mapper.LeaveeMapper;
import org.mapstruct.Mapper;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServices {

    private  EmployeeDAO employeeDAO;
    private  EmpoyeeMapper empoyeeMapper;

    private AddressMapper addressMapper;
//    private LeaveeMapper leaveeMapper;

    private DepartmentDAO departmentDAO;
    private AddressDAO addressDAO;
    private LeaveeDAO leaveeDAO;

    public EmployeeServices() {
        this.employeeDAO = new EmployeeDAO();;
        this.empoyeeMapper = EmpoyeeMapper.INSTANCE;

//        this.leaveeMapper = LeaveeMapper.INSTANCE;
        this.addressMapper = AddressMapper.INSTANCE;

        this.departmentDAO = new DepartmentDAO();
        this.addressDAO = new AddressDAO();
        this.leaveeDAO = new LeaveeDAO();
    }

    public List<EmployeeDto> getAllEmployees() {
        try {
            return Database.doInTransaction(em -> {

            //returns list of employee entity that works with database
            //so we will use (mapper struct) to change it to employeeDto and use it in the app
            List<Employee> employees = employeeDAO.getAllEmployees(em);
            System.out.println("I'm here public  List<EmployeeDto> getAllEmployees() in EmployeeServices Class");
            System.out.println("EmpoyeeMapper ---> "+ empoyeeMapper);
            return empoyeeMapper.toListDto(employees);
        });
        } catch (Exception e) {
            System.out.println("Failed to retrieve employees from the database"+ e);

            throw new RuntimeException("Failed to retrieve employees", e);
        }
    }

    public void addEmployee(EmployeeDto employeeDto) {

        Employee employee = empoyeeMapper.toEntity(employeeDto);

        // Set Department if provided
        if (employeeDto.getDepartmentId() != null) {
            Department department = Database.doInTransaction(em -> {
                return departmentDAO.findById(employeeDto.getDepartmentId(), em);
            });
            employee.setDepartment(department);
        }

        // Set Addresses if provided
        if (employeeDto.getAddresses() != null && !employeeDto.getAddresses().isEmpty()) {
            Set<Address> addresses = new LinkedHashSet<>();
            for (AddressDto addressDto : employeeDto.getAddresses()) {
                Address address = Database.doInTransaction(em -> {
                    return addressDAO.findById(addressDto.getId(), em);
                });
                if (address != null) {
                    addresses.add(address);
                } else {
                    throw new IllegalArgumentException("Address with ID " + addressDto.getId() + " not found");
                }
            }
            employee.setAddresses(addresses);
        }

        // Set Leavees if provided
        if (employeeDto.getLeavees() != null && !employeeDto.getLeavees().isEmpty()) {
            Set<Leavee> leavees = new LinkedHashSet<>();
            for (LeaveeDto leaveeDto : employeeDto.getLeavees()) {
                Leavee leavee = Database.doInTransaction(em -> {
                    return leaveeDAO.findById(leaveeDto.getId(), em);
                });
                if (leavee != null) {
                    leavees.add(leavee);
                } else {
                    throw new IllegalArgumentException("Leavee with ID " + leaveeDto.getId() + " not found");
                }
            }
            employee.setLeavees(leavees);
        }

        Database.doInTransactionWithoutResult(em -> {
            employeeDAO.save(employee, em);
        });


    }





//--------------------------------------------------------------------------------------------------

//    public  List<EmployeeDto> getAllEmployees() {
//        return Database.doInTransaction(em -> {
//            EmployeeDAO userDAO = new EmployeeDAO();
//
//            //returns list of employee entity that works with database
//            //so we will use (mapper struct) to change it to employeeDto and use it in the app
//            List<Employee> employees = userDAO.getAllEmployees(em);
//            System.out.println("I'm here public  List<EmployeeDto> getAllEmployees() in EmployeeServices Class");
//            System.out.println("EmpoyeeMapper ---> "+EmpoyeeMapper.INSTANCE.toListDto(employees));
//            return EmpoyeeMapper.INSTANCE.toListDto(employees);
//        });
//    }

//    public static void main(String[] args) {
//        System.out.println("Hello Hajar ......");
//        for (EmployeeDto emp : getAllEmployees()){
//            System.out.println("employee = "+emp);
//        }
//    }
}

/*
*
* Address
*
* if (employeeDto.getAddresses() != null && !employeeDto.getAddresses().isEmpty()) {
            Set<Address> addresses = new LinkedHashSet<>();
            Set<AddressDto> addressesDto = employeeDto.getAddresses();
            addresses = addressMapper.toSetEntity(addressesDto);
//            for (Integer addressId : employeeDto.getAddressIds()) {
//                Address address = addressDAO.findById(addressId);
//                if (address != null) {
//                    addresses.add(address);
//                }
//            }
            employee.setAddresses(addresses);
        }
        *
        *
* */
