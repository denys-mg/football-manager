package com.example.footballmanager.dao;

import com.example.footballmanager.model.Team;
import java.util.List;
import java.util.Optional;

public interface TeamDao {
    Team add(Team team);

    Optional<Team> get(Long id);

    List<Team> getAll();

    Team update(Team team);

    void delete(Long id);
}
