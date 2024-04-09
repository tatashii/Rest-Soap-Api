package org.api.rest.restApiServices;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.services.DepartmentServices;

import java.util.List;

@Path("department")
public class DepartmentRestAPI {

    private DepartmentServices departmentServices;

    public DepartmentRestAPI() {
        this.departmentServices = new DepartmentServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepartmentDto> getAllDepartments() {
        return departmentServices.getAllDepartments();
    }
}
