package main.Model.Dao;

import main.Model.Protocol;
import main.Model.Violation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProtocolDao extends GenericDao<Protocol> {
    protected EntityManager em;

    public ProtocolDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("persistenceunit");
        em = ef.createEntityManager();
    }
    @Override
    public Optional<Protocol> get(int id) {
        return Optional.of(em.find(Protocol.class, id));
    }

    @Override
    public List<Protocol> getAll() {
        return em.createNamedQuery("Protocol.findAll", Protocol.class).getResultList();
    }

    @Override
    public void save(Protocol protocol) {
        em.getTransaction().begin();
        em.persist(protocol);
        em.getTransaction().commit();
    }

    @Override
    public void update(Protocol protocol) {
        save(protocol);
    }

    @Override
    public void delete(Protocol protocol) {
        em.getTransaction().begin();
        em.remove(protocol);
        em.getTransaction().commit();
    }
}