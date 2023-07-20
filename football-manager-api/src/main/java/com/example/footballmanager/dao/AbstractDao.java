package com.example.footballmanager.dao;

import com.example.footballmanager.exception.DataProcessingException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public T add(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + clazz.getSimpleName()
                    + " " + element + " to database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get " + clazz.getSimpleName()
                    + ", by id: " + id + " from database", e);
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + clazz.getSimpleName(), clazz)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all "
                    + clazz.getSimpleName() + "s from database", e);
        }
    }

    public T update(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update " + clazz.getSimpleName()
                    + " " + element + " in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            T elementForDeleting = session.get(clazz, id);
            session.remove(elementForDeleting);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete " + clazz.getSimpleName()
                    + ", by id: " + id + " from database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
