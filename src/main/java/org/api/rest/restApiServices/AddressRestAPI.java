package org.api.rest.restApiServices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.api.rest.dto.AddressDto;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.services.AddressServices;
import org.api.rest.services.DepartmentServices;

import java.util.List;

@Path("address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressRestAPI {

    private AddressServices addressServices;

    public AddressRestAPI() {
        this.addressServices = new AddressServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddressDto> getAllAddresss() {
        return addressServices.getAllAddresss();
    }

    @POST
    public boolean addAddress(AddressDto addressDto) {
        return addressServices.addAddress(addressDto);
    }

    @PUT
    public boolean updateAddress(AddressDto addressDto) {
        return addressServices.updateAddress(addressDto);
    }

    @DELETE
    @Path("{id}")
    public boolean deleteAddress(@PathParam("id") int addressId) {
        return addressServices.deleteAddress(addressId);
    }
}
