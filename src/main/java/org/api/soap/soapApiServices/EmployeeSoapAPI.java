package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.services.EmployeeServices;

import java.util.List;

@WebService
public class EmployeeSoapAPI {

    private EmployeeServices employeeServices;

    public EmployeeSoapAPI() {
        this.employeeServices = new EmployeeServices();
    }

    @WebMethod(operationName = "getAllEmployees" )
    @WebResult(name = "Employee")
    public List<EmployeeDto> getAllEmployees() {
        System.out.println("SOAP API: Getting all employees");
        return employeeServices.getAllEmployees();
    }

//    @WebMethod(operationName = "getEmployeeById")
//    @WebResult(name = "Employee")
//    public EmployeeDto getEmployeeById(@WebParam(name = "id") int id) {
//        System.out.println("SOAP API: Getting employee by ID: " + id);
//        return employeeServices.getEmployeeById(id);
//    }



}