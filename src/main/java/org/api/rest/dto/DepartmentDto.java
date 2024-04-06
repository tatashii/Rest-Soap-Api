package org.api.rest.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class DepartmentDto implements Serializable {
    private Integer id;
    private String name;
}
