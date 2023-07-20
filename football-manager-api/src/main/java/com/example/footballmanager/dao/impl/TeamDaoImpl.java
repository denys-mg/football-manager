package com.example.footballmanager.dao.impl;

import com.example.footballmanager.dao.AbstractDao;
import com.example.footballmanager.dao.TeamDao;
import com.example.footballmanager.model.Team;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl extends AbstractDao<Team> implements TeamDao {
    public TeamDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Team.class);
    }
}
