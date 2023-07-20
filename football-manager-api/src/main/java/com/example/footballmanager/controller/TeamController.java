package com.example.footballmanager.controller;

import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.service.mapper.TeamMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @PostMapping
    public TeamResponseDto create(@RequestBody @Valid TeamRequestDto teamDto) {
        Team team = teamMapper.toModel(teamDto);
        teamService.add(team);
        return teamMapper.toDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto get(@PathVariable Long id) {
        return teamMapper.toDto(teamService.get(id));
    }

    @GetMapping
    public List<TeamResponseDto> getAll() {
        return teamService.getAll().stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public TeamResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid TeamRequestDto teamDto
    ) {
        Team team = teamMapper.toModel(teamDto);
        team.setId(id);
        teamService.update(team);
        return teamMapper.toDto(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
