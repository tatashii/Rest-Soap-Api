package org.api.rest.restApiServices;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.services.EmployeeServices;
import org.mapstruct.Context;

import java.util.List;

@Path("employee")
public class EmployeeRestAPI {

    private  EmployeeServices employeeServices;

    public EmployeeRestAPI() {
        this.employeeServices = new EmployeeServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> getAllEmployees() {
        System.out.println("I'm here public List<EmployeeDto> getAllEmployees() in EmployeeRestAPI Class");
        System.out.println("employee 1 ----------> "+employeeServices.getAllEmployees().get(1));
        return employeeServices.getAllEmployees();
    }

}

/*
*
*  @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        System.out.println("I'm here public public Response getAllEmployees() in EmployeeRestAPI Class");
        List<EmployeeDto> employeeDtos = emp.getAllEmployees();

        for (EmployeeDto emp : employeeDtos){
            if(emp != null)
                System.out.println("employee ----->  = "+emp);
            else
                System.out.println("emp = null");

            }
        return Response.ok(employeeDtos).build();
    }

* */





