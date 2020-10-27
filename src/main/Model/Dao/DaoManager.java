package main.Model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoManager {
    private Connection connection;

    public void open() throws SQLException {
        try {
            if(this.connection == null || this.connection.isClosed())
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "sergey", "123");
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

    @Override
    protected void finalize() throws Throwable {
        try { this.close(); }
        finally { super.finalize(); }
    }
}
