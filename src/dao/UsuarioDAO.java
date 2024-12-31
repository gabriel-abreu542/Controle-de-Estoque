package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends ObjetoDAO<Usuario>{

    public UsuarioDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        sqlCriar = "CREATE TABLE IF NOT EXISTS usuarios ("+
                "id TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "adm BOOLEAN NOT NULL" +
                ");";
        sqlInserir = "INSERT INTO usuarios (id,nome,senha,adm) VALUES (?,?,?,?)";
        sqlBuscar = "SELECT * FROM usuarios WHERE id = ?";
        sqlUpdate = "UPDATE usuarios SET nome = ?, senha = ?, adm = ? WHERE id = ?";
        sqlRemover = "DELETE FROM usuarios WHERE id = ?";
        tabela = "usuarios";
    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Usuario objeto) throws SQLException {
        stmt.setString(1, objeto.getId());
        stmt.setString(2, objeto.getNome());
        stmt.setString(3, objeto.getSenha());
        stmt.setBoolean( 4, objeto.isAdm());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Usuario objeto) throws SQLException {

    }

    @Override
    public Usuario buscarNaTabela(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("id"),
                rs.getString("nome"),
                rs.getString("senha"),
                rs.getBoolean("adm")
        );
    }
}
