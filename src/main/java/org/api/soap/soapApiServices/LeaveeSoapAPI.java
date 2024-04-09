package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.services.LeaveeServices;

import java.util.List;
@WebService
public class LeaveeSoapAPI {

    private LeaveeServices leaveeServices;

    public LeaveeSoapAPI() {
        this.leaveeServices = new LeaveeServices();
    }

    @WebMethod(operationName = "getAllLeavees" )
    @WebResult(name = "Leavee")
    public List<LeaveeDto> getAllLeavees() {
        System.out.println("SOAP API: Getting all leavees");
        return leaveeServices.getAllLeavees();
    }

    @WebMethod
    public boolean addLeavee(LeaveeDto leaveeDto) {
        return leaveeServices.addLeavee(leaveeDto);
    }

    @WebMethod
    public boolean updateLeavee(LeaveeDto leaveeDto) {
        return leaveeServices.updateLeavee(leaveeDto);
    }

    @WebMethod
    public boolean deleteLeavee(int leaveeId) {
        return leaveeServices.deleteLeavee(leaveeId);
    }
}
