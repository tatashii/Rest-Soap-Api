package org.api.rest.mapper;

import org.api.rest.dto.AddressDto;
import org.api.rest.dto.EmployeeDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);
    Set<AddressDto> toSetDto (Set<Address> addresss);
    Set<Address> toSetEntity (Set<AddressDto> AddressDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressDto addressDto, @MappingTarget Address address);
}


