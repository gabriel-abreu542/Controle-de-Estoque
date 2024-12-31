package dao;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends ObjetoDAO<Cliente>{
    public ClienteDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        sqlCriar = "CREATE TABLE IF NOT EXISTS clientes ("+
                "cpf TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "endividado BOOLEAN NOT NULL," +
                "divida REAL" +
                ");";
        sqlBuscar = "SELECT * FROM clientes WHERE cpf = ?";
        sqlInserir = "INSERT INTO clientes (cpf,nome,telefone,endividado,divida) VALUES (?,?,?,?,?)";
        sqlRemover = "DELETE FROM clientes WHERE cpf = ?";
        sqlUpdate = "UPDATE clientes SET nome = ?, telefone = ?, endividado = ?, divida = ? WHERE cpf = ?";
        tabela = "clientes";
    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Cliente objeto) throws SQLException {
        stmt.setString(1, objeto.getId());
        stmt.setString(2, objeto.getNome());
        stmt.setString(3, objeto.getTelefone());
        stmt.setBoolean( 4, objeto.temDivida());
        stmt.setFloat(5, objeto.getDivida());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Cliente objeto) throws SQLException{
        stmt.setString(1, objeto.getNome());
        stmt.setString(2, objeto.getTelefone());
        stmt.setBoolean(3, objeto.temDivida());
        stmt.setFloat(4, objeto.getDivida());
        stmt.setString(5, objeto.getId());
    }

    @Override
    public Cliente buscarNaTabela(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("telefone")
        );
        boolean endividado = rs.getBoolean("endividado");
        float divida = rs.getFloat("divida");

        if(endividado && divida > 0){
            cliente.setDivida(divida);
        };

        return cliente;
    }
}
