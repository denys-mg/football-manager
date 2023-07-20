package com.example.footballmanager.service.mapper;

import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(target = "id", ignore = true)
    Team toModel(TeamRequestDto teamDto);

    TeamResponseDto toDto(Team team);
}
