package com.example.footballmanager.service.impl;

import com.example.footballmanager.dao.TeamDao;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.TeamService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao;

    @Override
    public Team add(Team team) {
        return teamDao.add(team);
    }

    @Override
    public Team get(Long id) {
        return teamDao.get(id).orElseThrow(
                () -> new NoSuchElementException("No team found by id: " + id));
    }

    @Override
    public List<Team> getAll() {
        return teamDao.getAll();
    }

    @Override
    public Team update(Team team) {
        return teamDao.update(team);
    }

    @Override
    public void delete(Long id) {
        teamDao.delete(id);
    }
}
