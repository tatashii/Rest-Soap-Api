package org.api.rest.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Data
public class WorkDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Date startTime;
    private Date deadLine;
    private String status;
    private String type;
}