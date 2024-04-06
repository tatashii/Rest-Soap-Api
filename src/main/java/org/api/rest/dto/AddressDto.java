package org.api.rest.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class AddressDto  implements Serializable {
    private Integer id;
    private String houseNo;
    private String street;
    private String city;
    private String pincode;
    private String state;
    private Integer employeeId;
}
