package org.api.rest.services;

import org.api.rest.dao.impl.EmployeeDAO;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.entity.Employee;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.EmpoyeeMapper;

import java.util.List;

public class EmployeeServices {

    private  EmployeeDAO employeeDAO;
    private  EmpoyeeMapper empoyeeMapper;

    public EmployeeServices() {
        this.employeeDAO = new EmployeeDAO();;
        this.empoyeeMapper = EmpoyeeMapper.INSTANCE;
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
