package main.Model.Dao;

import main.Model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CarDao extends GenericDao<Car> {

    protected CarDao(Connection connection) {
        super(connection, "car");
    }

    String getInsertStatement(Car car) {
        return "INSERT INTO " + tableName + " (name, price)" +
                "VALUES ('" + car.getName() + "', " + car.getPrice() + ") RETURNING id;";
    }
    String getUpdateStatement(Car car) {
        return "UPDATE " + tableName + " SET name = '" + car.getName() + "'"
                + ", price = " + car.getPrice() + " WHERE id = " + car.getId() + ";";
    }

    Car getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int carId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        return new Car(carId, name, price);
    }

    String getDeleteStatement(Car car) {
        return "DELETE FROM " + tableName + " WHERE id = " +car.getId() + ";";
    }
}
