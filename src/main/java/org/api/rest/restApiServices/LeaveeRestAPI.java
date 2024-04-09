package org.api.rest.restApiServices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.services.LeaveeServices;
import org.api.rest.services.LeaveeServices;

import java.util.List;

@Path("leavee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeaveeRestAPI {

    private LeaveeServices leaveeServices;

    public LeaveeRestAPI() {
        this.leaveeServices = new LeaveeServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LeaveeDto> getAllLeavees() {
        return leaveeServices.getAllLeavees();
    }

    @POST
    public boolean addLeavee(LeaveeDto leaveeDto) {
        return leaveeServices.addLeavee(leaveeDto);
    }

    @PUT
    public boolean updateLeavee(LeaveeDto leaveeDto) {
        return leaveeServices.updateLeavee(leaveeDto);
    }

    @DELETE
    @Path("{id}")
    public boolean deleteLeavee(@PathParam("id") int leaveeId) {
        return leaveeServices.deleteLeavee(leaveeId);
    }

}
