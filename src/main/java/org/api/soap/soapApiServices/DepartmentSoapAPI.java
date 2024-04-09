package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.services.DepartmentServices;

import java.util.List;
@WebService
public class DepartmentSoapAPI {

    private DepartmentServices departmentServices;

    public DepartmentSoapAPI() {
        this.departmentServices = new DepartmentServices();
    }

    @WebMethod(operationName = "getAllDepartments" )
    @WebResult(name = "Department")
    public List<DepartmentDto> getAllDepartments() {
        System.out.println("SOAP API: Getting all departments");
        return departmentServices.getAllDepartments();
    }

    @WebMethod
    public Response addDepartment(@WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        try {
            boolean success = departmentServices.addDepartment(departmentDto);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add department").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @WebMethod
    public Response updateDepartment(@WebParam(name = "id") int id, @WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        try {
            departmentDto.setId(id);
            boolean success = departmentServices.updateDepartment(departmentDto);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update department").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @WebMethod
    public Response deleteDepartment(@WebParam(name = "id") int id, @WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        try {
            departmentDto.setId(id);
            boolean success = departmentServices.deleteDepartment(departmentDto);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete department").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }
}
