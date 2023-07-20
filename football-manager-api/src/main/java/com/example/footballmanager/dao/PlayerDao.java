package com.example.footballmanager.dao;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Player add(Player player);

    Optional<Player> get(Long id);

    List<Player> getAll();

    List<Player> getAllByTeam(Long teamId);

    Player update(Player player);

    void delete(Long id);

    Player transferToAnotherTeam(Player player, Team oldTeam, Team newTeam);
}
