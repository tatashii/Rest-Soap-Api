package org.api.soap.soapApiServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.api.rest.dto.AddressDto;
import org.api.rest.services.AddressServices;

import java.util.List;

@WebService
public class AddressSoapAPI {

    private AddressServices addressServices;

    public AddressSoapAPI() {
        this.addressServices = new AddressServices();
    }

    @WebMethod(operationName = "getAllAddresss" )
    @WebResult(name = "Address")
    public List<AddressDto> getAllAddresss() {
        System.out.println("SOAP API: Getting all addresss");
        return addressServices.getAllAddresss();
    }

    @WebMethod
    public boolean addAddress(@WebParam(name = "addressDto") AddressDto addressDto) {
        return addressServices.addAddress(addressDto);
    }

    @WebMethod
    public boolean updateAddress(@WebParam(name = "addressDto") AddressDto addressDto) {
        return addressServices.updateAddress(addressDto);
    }

    @WebMethod
    public boolean deleteAddress(@WebParam(name = "id") int addressId) {
        return addressServices.deleteAddress(addressId);
    }

}
