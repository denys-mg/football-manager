package com.example.footballmanager.service.mapper;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", source = "teamId", qualifiedByName = "mapTeam")
    Player toModel(PlayerRequestDto playerDto);

    @Mapping(target = "teamId", source = "team.id")
    PlayerResponseDto toDto(Player player);

    @Named("mapTeam")
    default Team mapTeam(Long teamId) {
        if (teamId == null) {
            return null;
        }
        Team team = new Team();
        team.setId(teamId);
        return team;
    }
}
