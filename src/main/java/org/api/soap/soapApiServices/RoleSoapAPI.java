package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.RoleDto;
import org.api.rest.services.RoleServices;

import java.util.List;
@WebService
public class RoleSoapAPI {
    private RoleServices roleServices;

    public RoleSoapAPI() {
        this.roleServices = new RoleServices();
    }

    @WebMethod(operationName = "getAllRoles" )
    @WebResult(name = "Role")
    public List<RoleDto> getAllRoles() {
        System.out.println("SOAP API: Getting all roles");
        return roleServices.getAllRoles();
    }

    @WebMethod
    public Response addRole(@WebParam(name = "roleDto") RoleDto roleDto) {
        boolean success = roleServices.addRole(roleDto);
        return success ? Response.ok().build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add role").build();
    }

    @WebMethod
    public Response updateRole(@WebParam(name = "id") int id, @WebParam(name = "roleDto") RoleDto roleDto) {
        roleDto.setId(id);
        boolean success = roleServices.updateRole(roleDto);
        return success ? Response.ok().build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update role").build();
    }

    @WebMethod
    public Response deleteRole(@WebParam(name = "id") int id) {
        boolean success = roleServices.deleteRole(id);
        return success ? Response.ok().build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete role").build();
    }
}
