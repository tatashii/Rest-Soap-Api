package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;
import org.api.rest.dto.WorkDto;
import org.api.rest.services.WorkServices;

import java.util.List;

@WebService
public class WorkSoapAPI {
    private WorkServices workServices;

    public WorkSoapAPI() {
        this.workServices = new WorkServices();
    }

    @WebMethod(operationName = "getAllWorks" )
    @WebResult(name = "Work")
    public List<WorkDto> getAllWorks() {
        System.out.println("SOAP API: Getting all works");
        return workServices.getAllWorks();
    }

    @WebMethod
    public Response addWork(@WebParam(name = "workDto") WorkDto workDto) {
        boolean success = workServices.addWork(workDto);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add work")
                    .build();
        }
    }

    @WebMethod
    public Response updateWork(@WebParam(name = "workDto") WorkDto workDto) {
        boolean success = workServices.updateWork(workDto);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update work")
                    .build();
        }
    }

    @WebMethod
    public Response deleteWork(@WebParam(name = "workId") int workId) {
        boolean success = workServices.deleteWork(workId);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete work")
                    .build();
        }
    }
}

