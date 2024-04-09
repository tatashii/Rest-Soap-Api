package org.api.rest.mapper;

import org.api.rest.dto.AddressDto;
import org.api.rest.dto.RoleDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role toEntity(RoleDto roleDto);//Role role  RoleDto roleDto

    RoleDto toDto(Role role);
    Set<RoleDto> toSetDto (Set<Role> role);
    Set<Role> toSetEntity (Set<RoleDto> RoleDto);

    List<RoleDto> toListDto (List<Role> roles);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);
}
