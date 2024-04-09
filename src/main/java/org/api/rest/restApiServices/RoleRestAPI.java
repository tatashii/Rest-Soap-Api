package org.api.rest.restApiServices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.RoleDto;
import org.api.rest.services.RoleServices;

import java.util.List;

@Path("role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleRestAPI {

    private RoleServices roleServices;

    public RoleRestAPI() {
        this.roleServices = new RoleServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoleDto> getAllRoles() {
        return roleServices.getAllRoles();
    }

    @POST
    public Response addRole(RoleDto roleDto) {
        try {
            boolean success = roleServices.addRole(roleDto);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add role").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRole(@PathParam("id") int id, RoleDto roleDto) {
        try {
            roleDto.setId(id);
            boolean success = roleServices.updateRole(roleDto);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update role").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRole(@PathParam("id") int id) {
        try {
            boolean success = roleServices.deleteRole(id);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete role").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }
}

