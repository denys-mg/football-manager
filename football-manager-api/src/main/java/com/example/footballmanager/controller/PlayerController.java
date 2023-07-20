package com.example.footballmanager.controller;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.mapper.PlayerMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    @PostMapping
    public PlayerResponseDto create(@RequestBody @Valid PlayerRequestDto playerDto) {
        Player player = playerMapper.toModel(playerDto);
        playerService.add(player);
        return playerMapper.toDto(player);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto get(@PathVariable Long id) {
        return playerMapper.toDto(playerService.get(id));
    }

    @GetMapping
    public List<PlayerResponseDto> getAll() {
        return playerService.getAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @GetMapping("/team/{teamId}")
    public List<PlayerResponseDto> getAllByTeam(@PathVariable Long teamId) {
        return playerService.getAllByTeam(teamId).stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid PlayerRequestDto playerDto
    ) {
        Player player = playerMapper.toModel(playerDto);
        player.setId(id);
        return playerMapper.toDto(playerService.update(player));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }

    @PutMapping("/transfer")
    public PlayerResponseDto transferToAnotherTeam(
            @RequestParam Long playerId,
            @RequestParam Long teamId
    ) {
        return playerMapper.toDto(playerService.transferToAnotherTeam(playerId, teamId));
    }
}
