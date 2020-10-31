package main.Model.Dao;

import javax.enterprise.inject.Disposes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoManager {
    private Connection connection;

    public void open() throws SQLException {
        try {
            if(this.connection == null || this.connection.isClosed())
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "sergey", "123");
            createTables(this.connection);
        }
        catch(SQLException e) { throw e; }
    }

    public void close() throws SQLException {
        try {
            if(this.connection != null && !this.connection.isClosed())
                this.connection.close();
        }
        catch(SQLException e) { throw e; }
    }

    private DaoManager() {

    }

    private static class DaoManagerSingleton {
        public static final DaoManager instance;
        static {
            DaoManager dm;
            try {
                dm = new DaoManager();
            }
            catch(Exception e) {
                dm = null;
            }
            instance = dm;
        }

    }

    public static DaoManager shared() {
        return DaoManagerSingleton.instance;
    }

    public GenericDao getDAO(Table table) throws SQLException {
        try {
            if (connection == null || connection.isClosed())
                open();
        }
        catch(SQLException exception) { throw exception; }

        switch(table) {
            case Car:
                return new CarDao(connection);
            case Protocol:
                return new ProtocolDao(connection);
            case Violation:
                return new ViolationDao(connection);
            default:
                throw new SQLException("Trying to link to an unexistant table.");
        }
    }

    private void createTable(Connection connection, String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            boolean success = statement.execute(sqlStatement);
            if(!success) {
                System.out.println("FAILED CREATE TABLE SQLSTATEMENT:" + sqlStatement);
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    private void createTables(Connection connection) throws SQLException {
        // Violation Table
        createTable(connection, "CREATE TABLE IF NOT EXISTS Violation (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   name VARCHAR(50),\n" +
                "   fine_sum INT\n" +
                ");\n");
        // Car Table
        createTable(connection,"CREATE TABLE IF NOT EXISTS Car (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   name VARCHAR(50),\n" +
                "   price INT\n" +
                ");\n");
        // Protocol Table
        createTable(connection,"CREATE TABLE IF NOT EXISTS Protocol (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   id_car INT,\n" +
                "   id_violation INT,\n" +
                "   FOREIGN KEY(id_car) REFERENCES Car(id),\n" +
                "   FOREIGN KEY(id_violation) REFERENCES Violation(id)\n" +
                ");\n");
    }

}
