package service;

import java.rmi.ConnectIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private Connection connection;

    public static void connect() throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:baseDados.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão com SQLite estabelecida");
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if (conn != null) {
                    conn.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection(){

    }
}
