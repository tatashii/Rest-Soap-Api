package org.api.rest.restApiServices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.services.DepartmentServices;

import java.util.List;

@Path("department")
public class DepartmentRestAPI {

    private DepartmentServices departmentServicesss;

    public DepartmentRestAPI() {
        this.departmentServicesss = new DepartmentServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepartmentDto> getAllDepartments() {
        return departmentServicesss.getAllDepartments();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // for return results
    public Response deleteDepartment(@PathParam("id") int id, DepartmentDto departmentDto) {
        try {
            departmentDto.setId(id);
            boolean success = departmentServicesss.deleteDepartment(departmentDto);
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

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, DepartmentDto departmentDto) {
        try {
            departmentDto.setId(id);
            boolean success = departmentServicesss.updateDepartment(departmentDto);
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDepartment(DepartmentDto departmentDto) {
//        try {
            boolean success = departmentServicesss.addDepartment(departmentDto);
//            if (success) {
                return Response.ok().build();
//            } else {
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add department").build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }

}

