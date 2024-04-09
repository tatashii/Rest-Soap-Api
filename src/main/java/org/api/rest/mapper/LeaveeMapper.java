package org.api.rest.mapper;

import org.api.rest.dto.AddressDto;
import org.api.rest.dto.LeaveeDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Leavee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface LeaveeMapper {

    LeaveeMapper INSTANCE = Mappers.getMapper(LeaveeMapper.class);
    Leavee toEntity(LeaveeDto leaveeDto);

    LeaveeDto toDto(Leavee leavee);
    Set<LeaveeDto> toSetDto (Set<Leavee> leavees);
    Set<Leavee> toSetEntity (Set<LeaveeDto> leaveeDtos);

    List<LeaveeDto> toListDto (List<Leavee> leavees);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Leavee partialUpdate(LeaveeDto leaveeDto, @MappingTarget Leavee leavee);


}
