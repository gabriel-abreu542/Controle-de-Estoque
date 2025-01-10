package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompraDAO extends TransacaoDAO{
    public CompraDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        tabela = "compras";
        sqlCriar = "CREATE TABLE IF NOT EXISTS compras ("+
                "id TEXT PRIMARY KEY," +
                "formaPagamento TEXT CHECK( formaPagamento IN ('DINHEIRO','DEBITO','PIX','CREDITO') ) NOT NULL," +
                "data DATE NOT NULL," +
                "total REAL NOT NULL," +
                "cnpjFornecedor TEXT," +
                "FOREIGN KEY (cnpjFornecedor) REFERENCES clientes(id)" +
                ");";
        sqlBuscar = "SELECT * FROM compras WHERE id = ?";
        sqlInserir = "INSERT INTO compras (id, formaPagamento, data, total, cnpjFornecedor) VALUES (?,?,?,?,?)";
        sqlRemover = "DELETE FROM compras WHERE id = ?";
        sqlUpdate = "UPDATE compras SET formaPagamento = ?, data = ?, total = ?, cnpjFornecedor = ?, WHERE id = ?";
    }

    @Override
    public void setSQLItens() {
        tabelaItens = "itensCompra";
        sqlCriarItens = "CREATE TABLE IF NOT EXISTS itensCompra ("+
                "idCompra TEXT," +
                "idProduto TEXT," +
                "quantidade INT NOT NULL," +
                "precoUnitario REAL NOT NULL" +
                //  "total parcial REAL NOT NULL" +      // decidir se total parcial será calculado ou armazenado
                "PRIMARY KEY (idCompra, idProduto)," +
                "FOREIGN KEY (idCompra) REFERENCES compras(id)," +
                "FOREIGN KEY (idProduto) REFERENCES produtos(id)" +
                ");";
        sqlBuscarItens = "SELECT * FROM itensCompra WHERE idCompra = ?";
        sqlInserirItem = "INSERT into itensCompra (idCompra,idProduto,quantidade,precoUnitario) VALUES (?,?,?,?)";
        sqlRemoverDeItens = "DELETE FROM itensCompra WHERE idCompra = ?";
    }


    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Transacao objeto) throws SQLException {
        Compra novaCompra = (Compra) objeto;
        stmt.setString(1, novaCompra.getId());
        stmt.setString(2, novaCompra.getFormaPagamento());
        stmt.setString(3, novaCompra.getDataTransacao().toString());
        stmt.setFloat( 4, novaCompra.getValorTotal());
        stmt.setString(5, novaCompra.getFornecedor().getId());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Transacao objeto) throws SQLException {

    }

    @Override
    public Compra buscarNaTabela(ResultSet rs) throws SQLException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
        Compra compra = null;
        Fornecedor fornecedor = fornecedorDAO.buscarPorId(rs.getString("cnpjFornecedor"));
        if (fornecedor == null) {
            throw new SQLException("Fornecedor não encontrado para o ID: " + rs.getString("cnpjFornecedor"));
        }
        compra = new Compra(
                rs.getString("id"),
                fornecedor,
                rs.getString("formaPagamento")
        );
        compra.setDataTransacao(LocalDate.parse(rs.getString("data")));

        return compra;
    }
}
