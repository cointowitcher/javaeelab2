package main.Model.Dao;

import main.Model.Violation;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ViolationDao extends GenericDao<Violation> {

    protected EntityManager em;

    public ViolationDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("persistenceunit");
        em = ef.createEntityManager();
    }

    @Override
    public Optional<Violation> get(int id) {
        return Optional.of(em.find(Violation.class, id));
    }

    @Override
    public List<Violation> getAll() {
        return em.createNamedQuery("Violation.findAll", Violation.class).getResultList();
    }

    @Override
    public void save(Violation violation) {
        em.getTransaction().begin();
        em.persist(violation);
        em.getTransaction().commit();
    }

    @Override
    public void update(Violation violation) {
        save(violation);
    }

    @Override
    public void delete(Violation violation) {
        em.getTransaction().begin();
        em.remove(violation);
        em.getTransaction().commit();
    }
}
