package main.Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDao<T> {

    protected final String tableName;
    protected Connection connection;

    abstract T getObjectFromResultSet(ResultSet resultSet) throws SQLException;
    abstract String getInsertStatement(T object);
    abstract String getUpdateStatement(T object);
    abstract String getDeleteStatement(T object);

    String getGetQuery(String id) {
        return "SELECT * FROM " + tableName + " WHERE id = " + id + ";";
    }
    String getAllQuery() {
        return "SELECT * FROM " + tableName + ";";
    }
    public Optional<T> get(int id) {
        String sql = getGetQuery(Integer.toString(id));
        Optional<T> car = Optional.empty();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            rs.next();
            car = Optional.of(getObjectFromResultSet(rs));
        } catch(SQLException ex) { ex.printStackTrace(); }

        return car;
    }

    public List<T> getAll() {
        String sql = getAllQuery();
        List<T> list = new ArrayList<T>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                T object = getObjectFromResultSet(rs);
                list.add(object);
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public Optional<Integer> save(T t) {
        Optional<Integer> savedId = Optional.empty();
        String sql = getInsertStatement(t);
        System.out.println("sql: " + sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            savedId = Optional.of(statement.executeUpdate());
        } catch(SQLException ex) { ex.printStackTrace(); }

        return savedId;
    }

    public void update(T t) {
        String sql = getUpdateStatement(t);
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("On update modify more than 1 record: " + count);
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
    };
    public void delete(T t) {
        String sql = getDeleteStatement(t);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int count = statement.executeUpdate();
            if (count != 1) {
                System.out.println("On update modify more than 1 record: " + count);
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
    };

    protected GenericDao(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }
}
