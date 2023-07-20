package com.example.footballmanager.dao.impl;

import com.example.footballmanager.dao.AbstractDao;
import com.example.footballmanager.dao.PlayerDao;
import com.example.footballmanager.exception.DataProcessingException;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDaoImpl extends AbstractDao<Player> implements PlayerDao {
    public PlayerDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Player.class);
    }

    @Override
    public Optional<Player> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery("""
                FROM Player p
                LEFT JOIN FETCH p.team
                WHERE p.id = :id
                    """, Player.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get player with id: " + id, e);
        }
    }

    @Override
    public Player transferToAnotherTeam(Player player, Team oldTeam, Team newTeam) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(oldTeam);
            session.merge(newTeam);
            session.merge(player);
            transaction.commit();
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't transfer player: " + player + " to team: "
                    + newTeam + " in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Player> getAllByTeam(Long teamId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery("""
                    FROM Player p
                    JOIN FETCH p.team
                    WHERE p.team.id = :teamId
                        """, Player.class);
            query.setParameter("teamId", teamId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all players for team with id: "
                    + teamId, e);
        }
    }
}
