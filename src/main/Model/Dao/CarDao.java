package main.Model.Dao;

import main.Model.Car;
import main.Model.Protocol;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class CarDao extends GenericDao<Car> {
    @PersistenceContext(name = "postgrespersistenceq")
    public EntityManager em;

    @Override
    public Optional<Car> get(int id) {
        return Optional.of(em.find(Car.class, id));
    }

    @Override
    public List<Car> getAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public List<String> getUpperedNames() {
        return em.createQuery("select UPPER(c.name) from Car c", String.class).getResultList();
    }

    public List<Car> getOrderedByAlphabetCars() {
        return em.createQuery("select c from Car c order by c.name ASC", Car.class).getResultList();
    }
    public Optional<Car> findCarByInsesitiveCaseName(String name) {
        return Optional.of(em.createQuery("select c from Car c where lower(c.name)=" + name.toLowerCase(), Car.class).getSingleResult());
    }

    @Override
    public void save(Car car) {
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    @Override
    public void update(Car car) {
        save(car);
        updateDataInDb();
    }

    @Override
    public void delete(Car car) {
        em.getTransaction().begin();
        em.createQuery("delete from Car c where c.id = " + car.getId()).executeUpdate();
        em.getTransaction().commit();
    }

    @Asynchronous
    void updateDataInDb() {
        try {
            Thread.sleep(1000);
            System.out.println("Data in cloud have been updated");
            // updating data in cloud, retrieving result and depending on the result call other functions
        } catch(Exception exception) {
            // Schedule updating database for next iteration or other logic
            System.out.println("Data in cloud could not be updated");
        }
    }
}
