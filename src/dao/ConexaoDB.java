package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static String url = "jdbc:sqlite:baseDados.db";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = DriverManager.getConnection(url);
            System.out.println("Conexão com SQLite estabelecida (baseDados.db).");
        }
        return connection;
    }

    public static void setDatabaseUrl(String databaseUrl) {
        url = databaseUrl;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com SQLite encerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}