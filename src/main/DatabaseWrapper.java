package main;

import java.sql.*;

public class DatabaseWrapper {

    private static void createTable(String sqlStatement) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "sergey", "123");
            statement = connection.createStatement();
            Boolean success = statement.execute(sqlStatement);
            if(!success) {
                System.out.println("FAILED CREATE TABLE SQLSTATEMENT:" + sqlStatement);
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        } finally {
            try { connection.close(); } catch(SQLException exception) { };
            try { statement.close(); } catch(SQLException exception) { };
        }
    }
    public static void createTables() {

        // Violation Table
        createTable("CREATE TABLE IF NOT EXISTS Violation (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   name VARCHAR(50),\n" +
                "   fine_sum INT\n" +
                ");\n");
        // Car Table
        createTable("CREATE TABLE IF NOT EXISTS Car (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   name VARCHAR(50),\n" +
                "   price INT\n" +
                ");\n");
        // Protocol Table
        createTable("CREATE TABLE IF NOT EXISTS Protocol (\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   id_car INT,\n" +
                "   id_violation INT,\n" +
                "   FOREIGN KEY(id_car) REFERENCES Car(id),\n" +
                "   FOREIGN KEY(id_violation) REFERENCES Violation(id)\n" +
                ");\n");
    }

}
