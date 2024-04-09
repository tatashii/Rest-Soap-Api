package org.api.rest.restApiServices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.WorkDto;
import org.api.rest.services.WorkServices;

import java.util.List;

@Path("work")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkRestAPI {

    private WorkServices workServices;

    public WorkRestAPI() {
        this.workServices = new WorkServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkDto> getAllWorks() {
        return workServices.getAllWorks();
    }

    @POST
    public Response addWork(WorkDto workDto) {
        boolean success = workServices.addWork(workDto);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add work")
                    .build();
        }
    }

    @PUT
    public Response updateWork(WorkDto workDto) {
        boolean success = workServices.updateWork(workDto);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update work")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWork(@PathParam("id") int workId) {
        boolean success = workServices.deleteWork(workId);
//        if (success) {
            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Failed to delete work")
//                    .build();
//        }
    }
}
