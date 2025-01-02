package test.dao;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class VendaDAOTest {
    private VendaDAO vendaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();
        vendaDAO = new VendaDAO(connection);
        vendaDAO.criarTabela();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        clienteDAO.criarTabela();
        produtoDAO.criarTabela();

        Cliente cliente1 = new Cliente("C1", "Gabriel", "9999-9999");
        clienteDAO.inserir(cliente1);

        Produto produto1 = new Produto("P1","Parafuso", 0.50f, 1.00f);
        Produto produto2 = new Produto("P2","Cimento", 15.00f, 30.00f);
        produtoDAO.inserir(produto1);
        produtoDAO.inserir(produto2);

        Venda venda1 = new Venda("V1", cliente1, "DINHEIRO");
        venda1.adicionarItem(produto1, 30);
        venda1.adicionarItem(produto2, 3);
        vendaDAO.inserir(venda1);

        Cliente cliente2 = new Cliente("C2", "João", "8888-8888");
        clienteDAO.inserir(cliente2);

        Produto produto3 = new Produto("P3", "Tijolo", 2.00f, 3.50f);
        Produto produto4 = new Produto("P4", "Areia", 10.00f, 25.00f);
        produtoDAO.inserir(produto3);
        produtoDAO.inserir(produto4);

        Venda venda2 = new Venda("V2", cliente2, "CREDITO");
        venda2.adicionarItem(produto3, 50);
        venda2.adicionarItem(produto4, 10);
        vendaDAO.inserir(venda2);

        Venda venda3 = new Venda("V3", cliente2, "PIX");
        venda3.adicionarItem(produto4, 5);
        vendaDAO.inserir(venda3);

    }

    @AfterEach
    void tearDown() throws SQLException{
        Connection connection = ConexaoDBTest.getConnection();
        vendaDAO.deletarTabela();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        produtoDAO.deletarTabela();
        clienteDAO.deletarTabela();
    }

    @Test
    public void testBuscarVendaId(){
        Venda buscada = (Venda) vendaDAO.buscarPorId("V1");
        System.out.println(buscada);
    }

    @Test
    public void testListarVendas(){
        for (Transacao v : vendaDAO.listarTodos()){
            System.out.println("Item:");
            System.out.println(v);
        }
    }

    @Test
    public void testRemoverVenda(){
        vendaDAO.remover("V3");
        Venda buscada = (Venda) vendaDAO.buscarPorId("V3");
        assertNull(buscada);
    }

    @Test
    public void testAtualizarVenda(){

    }
}
