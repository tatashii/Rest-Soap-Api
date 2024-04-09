package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
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


    @WebMethod
    public String addEmployee(@WebParam(name = "employee") EmployeeDto employeeDto) {
        boolean added = employeeServices.addEmployee(employeeDto);
        if (added) {
            return "Employee added successfully.";
        } else {
            return "Failed to add employee.";
        }
    }

    @WebMethod
    public String updateEmployee(@WebParam(name = "employee") EmployeeDto employeeDto) {
        boolean updated = employeeServices.updateEmployee(employeeDto);
        if (updated) {
            return "Employee updated successfully.";
        } else {
            return "Failed to update employee.";
        }
    }

    @WebMethod
    public String deleteEmployee(@WebParam(name = "id") int id) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        boolean deleted = employeeServices.deleteEmployee(employeeDto);
        if (deleted) {
            return "Employee with ID " + id + " deleted successfully.";
        } else {
            return "Employee with ID " + id + " not found.";
        }
    }


}