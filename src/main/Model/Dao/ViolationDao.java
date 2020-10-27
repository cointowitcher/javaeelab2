package main.Model.Dao;

import main.Model.Protocol;
import main.Model.Violation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class ViolationDao extends GenericDao<Violation> {

    protected ViolationDao(Connection connection) {
        super(connection, "Violation");
    }

    String getInsertStatement(Violation violation) {
        return "INSERT INTO " + tableName + " (name, fine_sum)" +
                "VALUES ('" + violation.getName() + "', " + violation.getFineSum() + ") RETURNING id;";
    }
    String getUpdateStatement(Violation violation) {
        return "UPDATE " + tableName + " SET name = '" + violation.getName() + "'"
                + ", fine_sum = " + violation.getFineSum() + "WHERE id = " + violation.getId() + ";";
    }

    Violation getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int violationId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int fineSum = resultSet.getInt("fine_sum");
        return new Violation(violationId, name, fineSum);
    }

    String getDeleteStatement(Violation violation) {
        return "DELETE FROM " + tableName + " WHERE id = " +violation.getId() + ";";
    }
}
