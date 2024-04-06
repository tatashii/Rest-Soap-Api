package org.api.rest.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Value
public class LeaveeDto implements Serializable {
    private Integer id;
    private String reason;
    private String status;
    private Date leaveStartDate;
    private Date leaveEndDate;
    private Integer employeeId;
}
