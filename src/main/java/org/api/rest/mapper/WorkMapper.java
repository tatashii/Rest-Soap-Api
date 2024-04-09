package org.api.rest.mapper;

import org.api.rest.dto.AddressDto;
import org.api.rest.dto.WorkDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Work;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface WorkMapper {

    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);
    Work toEntity(WorkDto workDto);//Work work  WorkDto workDto

    WorkDto toDto(Work work);
    Set<WorkDto> toSetDto (Set<Work> work);
    Set<Work> toSetEntity (Set<WorkDto> WorkDto);

    List<WorkDto> toListDto (List<Work> works);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Work partialUpdate(WorkDto workDto, @MappingTarget Work work);

}
