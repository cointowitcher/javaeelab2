package main.Model.Dao;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDao<T> {
    abstract public Optional<T> get(int id);

    abstract public List<T> getAll();

    abstract public void save(T t);

    abstract public void update(T t);
    abstract public void delete(T t);
}
