package org.api.rest.mapper;
import lombok.Data;
import org.api.rest.dto.DepartmentDto;
import org.api.rest.entity.Department;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Department toEntity(DepartmentDto departmentDto);

    DepartmentDto toDto(Department department);
    Set<DepartmentDto> toSetDto (Set<Department> departments);
    List<DepartmentDto> toListDto (List<Department> departments);
    Set<Department> toSetEntity (Set<DepartmentDto> DepartmentDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentDto departmentDto, @MappingTarget Department department);

}
