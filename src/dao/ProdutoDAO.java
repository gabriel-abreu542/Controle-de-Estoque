package dao;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends ObjetoDAO<Produto>{
    public ProdutoDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        sqlCriar = "CREATE TABLE IF NOT EXISTS produtos ("+
                "id TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "desc TEXT," +
                "tipo TEXT," +
                "precoCompra REAL NOT NULL," +
                "precoVenda REAL NOT NULL" +
                ");";
        sqlInserir = "INSERT INTO produtos (id,nome,desc,tipo,precoCompra,precoVenda) VALUES (?,?,?,?,?,?)";
        sqlBuscar = "SELECT * FROM produtos WHERE id = ?";
        sqlRemover = "DELETE FROM produtos WHERE id = ?";
        sqlUpdate = "UPDATE produtos SET nome = ?, desc = ?, tipo = ?, precoCompra = ?,precoVenda = ? WHERE id = ?";
        tabela = "produtos";
    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Produto objeto) throws SQLException {
        stmt.setString(1, objeto.getId());
        stmt.setString(2, objeto.getNome());
        stmt.setString(3, objeto.getDesc());
        stmt.setString(4, objeto.getTipo());
        stmt.setFloat( 5, objeto.getPrecoCompra());
        stmt.setFloat( 6, objeto.getPrecoVenda());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Produto objeto) throws SQLException {
        stmt.setString(1, objeto.getNome());
        stmt.setString(2, objeto.getDesc());
        stmt.setString(3, objeto.getTipo());
        stmt.setFloat( 4, objeto.getPrecoCompra());
        stmt.setFloat( 5, objeto.getPrecoVenda());
        stmt.setString(6, objeto.getId());
    }

    @Override
    public Produto buscarNaTabela(ResultSet rs) throws SQLException {
        Produto produto = new Produto(
                rs.getString("id"),
                rs.getString("nome"),
                rs.getFloat("precoCompra"),
                rs.getFloat("precoVenda")
        );
        String desc = rs.getString("desc");
        String tipo = rs.getString("tipo");
        if(!desc.isEmpty()){
            produto.setDesc(desc);
        }
        if(!tipo.isEmpty()){
            produto.setTipo(tipo);
        }

        return produto;
    }
}
