package org.api.rest.mapper;

import org.api.rest.dto.EmployeeDto;
import org.api.rest.entity.Employee;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmpoyeeMapper {
    EmpoyeeMapper INSTANCE = Mappers.getMapper(EmpoyeeMapper.class);
    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);
    List<EmployeeDto> toListDto (List<Employee> employees);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee partialUpdate(EmployeeDto employeeDto, @MappingTarget Employee employee);


}


