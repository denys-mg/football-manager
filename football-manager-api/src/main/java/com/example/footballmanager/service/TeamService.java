package com.example.footballmanager.service;

import com.example.footballmanager.model.Team;
import java.util.List;

public interface TeamService {
    Team add(Team team);

    Team get(Long id);

    List<Team> getAll();

    Team update(Team team);

    void delete(Long id);
}
