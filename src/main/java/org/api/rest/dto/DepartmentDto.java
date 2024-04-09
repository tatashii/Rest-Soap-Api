package org.api.rest.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class DepartmentDto implements Serializable {
    private Integer id;
    private String name;
}
