package dao;

import model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO extends ObjetoDAO<Fornecedor>{
    public FornecedorDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        sqlCriar = "CREATE TABLE IF NOT EXISTS fornecedores ("+
                "cnpj TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "endereco TEXT NOT NULL" +
                ");";
        sqlBuscar = "SELECT * FROM fornecedores WHERE cnpj = ?";
        sqlInserir = "INSERT INTO fornecedores (cnpj,nome,telefone,email,endereco) VALUES (?,?,?,?,?)";
        sqlRemover = "DELETE FROM fornecedores WHERE cnpj = ?";
        sqlUpdate = "UPDATE fornecedores SET nome = ?, telefone = ?, email = ?, endereco = ? WHERE cnpj = ?";
        tabela = "fornecedores";
    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Fornecedor objeto) throws SQLException {
        stmt.setString(1, objeto.getId());
        stmt.setString(2, objeto.getNome());
        stmt.setString(3, objeto.getTelefone());
        stmt.setString( 4, objeto.getEmail());
        stmt.setString(5, objeto.getEndereco());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Fornecedor objeto) throws SQLException {
        stmt.setString(1, objeto.getNome());
        stmt.setString(2, objeto.getTelefone());
        stmt.setString(3, objeto.getEmail());
        stmt.setString(4, objeto.getEndereco());
        stmt.setString(5, objeto.getId());
    }

    @Override
    public Fornecedor buscarNaTabela(ResultSet rs) throws SQLException {
        return new Fornecedor(
                rs.getString("nome"),
                rs.getString("cnpj"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco")
        );
    }
}
