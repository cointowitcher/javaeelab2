package main.Model.Dao;

import main.Model.Protocol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class ProtocolDao extends GenericDao<Protocol> {

    protected ProtocolDao(Connection connection) {
        super(connection, "Protocol");
    }

    String getInsertStatement(Protocol protocol) {
        return "INSERT INTO " + tableName + " (id_car, id_violation)" +
                "VALUES (" + protocol.getCarId() + ", " + protocol.getViolationId() + ") RETURNING id;";
    }
    String getUpdateStatement(Protocol protocol) {
        return "UPDATE " + tableName + " SET id_car = " + protocol.getCarId()
                + ", id_violation = " + protocol.getViolationId() + "WHERE id = " + protocol.getId() + ";";
    }

    Protocol getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int violationId = resultSet.getInt("id");
        int idCar = resultSet.getInt("id_car");
        int idViolation = resultSet.getInt("id_violation");
        return new Protocol(violationId, idCar, idViolation);
    }

    String getDeleteStatement(Protocol protocol) {
        return "DELETE FROM " + tableName + " WHERE id = " +protocol.getId() + ";";
    }
}
