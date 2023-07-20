package com.example.footballmanager.service;

import com.example.footballmanager.model.Player;
import java.util.List;

public interface PlayerService {
    Player add(Player player);

    Player get(Long id);

    List<Player> getAll();

    List<Player> getAllByTeam(Long teamId);

    Player update(Player player);

    void delete(Long id);

    Player transferToAnotherTeam(Long id, Long teamId);
}
