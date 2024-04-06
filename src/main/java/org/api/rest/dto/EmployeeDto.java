package org.api.rest.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Value
public class EmployeeDto implements Serializable {
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String role;
    private String gender;
    private BigDecimal salary;
    private Date dob;
    private Date joining;
    private Integer departmentId;
    private Set<AddressDto> addresses;
    private Set<LeaveeDto> leavees;
}
