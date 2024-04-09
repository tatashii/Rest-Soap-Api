package org.api.rest.restApiServices;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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

    @POST
    @Consumes({"application/xml", "application/json"})
    public void addEmployee(EmployeeDto emplyeeDto) {
        employeeServices.addEmployee(emplyeeDto);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void updateEmployee(EmployeeDto emplyeeDto) {
        employeeServices.updateEmployee(emplyeeDto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        boolean deleted = employeeServices.deleteEmployee(employeeDto);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }




}





