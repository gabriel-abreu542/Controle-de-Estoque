package test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDBTest {
    private static final String url_teste = "jdbc:sqlite::memory:"; // Banco em memória
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url_teste);
            System.out.println("Conexao teste estabelecida com SQLite");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                System.out.println("Conexao SQLite teste encerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
