package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class VendaDAO extends TransacaoDAO{
    public VendaDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void setSQL() {
        tabela = "vendas";
        sqlCriar = "CREATE TABLE IF NOT EXISTS vendas ("+
                "id TEXT PRIMARY KEY," +
                "formaPagamento TEXT CHECK( formaPagamento IN ('DINHEIRO','DEBITO','PIX','CREDITO') ) NOT NULL," +
                "data DATE NOT NULL," +
                "total REAL NOT NULL," +
                "cpfCliente TEXT," +
                "FOREIGN KEY (cpfCliente) REFERENCES clientes(id)" +
                ");";
        sqlBuscar = "SELECT * FROM vendas WHERE id = ?";
        sqlInserir = "INSERT INTO vendas (id, formaPagamento, data, total, cpfCliente) VALUES (?,?,?,?,?)";
        sqlRemover = "DELETE FROM vendas WHERE id = ?";
        sqlUpdate = "UPDATE vendas SET formaPagamento = ?, data = ?, total = ?, cpfCliente = ?, WHERE id = ?";
    }

    @Override
    public void setSQLItens() {
        tabelaItens = "itensVenda";
        sqlCriarItens = "CREATE TABLE IF NOT EXISTS itensVenda ("+
                "idVenda TEXT," +
                "idProduto TEXT," +
                "quantidade INT NOT NULL," +
                "precoUnitario REAL NOT NULL," +
                //  "total parcial REAL NOT NULL" +      // decidir se total parcial será calculado ou armazenado
                "PRIMARY KEY (idVenda, idProduto)," +
                "FOREIGN KEY (idVenda) REFERENCES vendas(id)," +
                "FOREIGN KEY (idProduto) REFERENCES produtos(id)" +
                ");";
        sqlBuscarItens = "SELECT * FROM itensVenda WHERE idVenda = ?";
        sqlInserirItem = "INSERT INTO itensVenda (idVenda,idProduto,quantidade, precoUnitario) VALUES (?,?,?,?)";
        sqlRemoverDeItens = "DELETE FROM itensVenda WHERE idVenda = ?";
    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Transacao objeto) throws SQLException {
        Venda novaVenda = (Venda) objeto;
        stmt.setString(1, novaVenda.getId());
        stmt.setString(2, novaVenda.getFormaPagamento());
        stmt.setString(3, novaVenda.getDataTransacao().toString());
        stmt.setFloat( 4, novaVenda.getValorTotal());
        stmt.setString(5, novaVenda.getCliente().getId());
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Transacao objeto) throws SQLException {
        Venda venda = (Venda) objeto;
        stmt.setString(1, venda.getFormaPagamento());
        stmt.setString(2, venda.getDataTransacao().toString());
        stmt.setFloat( 3, venda.getValorTotal());
        stmt.setString(4, venda.getCliente().getId());
    }

    @Override
    public Transacao buscarNaTabela(ResultSet rs) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Venda venda = null;
        Cliente cliente = clienteDAO.buscarPorId(rs.getString("cpfCliente"));
        if (cliente == null) {
            throw new SQLException("Cliente não encontrado para o ID: " + rs.getString("cpfCliente"));
        }
        venda = new Venda(
                rs.getString("id"),
                cliente,
                rs.getString("formaPagamento")
        );
        venda.setDataTransacao(LocalDate.parse(rs.getString("data")));



        return venda;
    }

}
