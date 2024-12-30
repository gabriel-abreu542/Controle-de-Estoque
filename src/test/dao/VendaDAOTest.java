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
        vendaDAO.criarTabelasVendas();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        clienteDAO.criarTabelaClientes();
        produtoDAO.criarTabelaProdutos();

        Cliente cliente1 = new Cliente("C1", "Gabriel", "9999-9999");
        clienteDAO.inserirCliente(cliente1);

        Produto produto1 = new Produto("P1","Parafuso", 0.50f, 1.00f);
        Produto produto2 = new Produto("P2","Cimento", 15.00f, 30.00f);
        produtoDAO.inserirProduto(produto1);
        produtoDAO.inserirProduto(produto2);

        Venda venda1 = new Venda("V1", cliente1, "DINHEIRO");
        venda1.adicionarItem(produto1, 30);
        venda1.adicionarItem(produto2, 3);
        vendaDAO.inserirVenda(venda1);

        Cliente cliente2 = new Cliente("C2", "João", "8888-8888");
        clienteDAO.inserirCliente(cliente2);

        Produto produto3 = new Produto("P3", "Tijolo", 2.00f, 3.50f);
        Produto produto4 = new Produto("P4", "Areia", 10.00f, 25.00f);
        produtoDAO.inserirProduto(produto3);
        produtoDAO.inserirProduto(produto4);

        Venda venda2 = new Venda("V2", cliente2, "CREDITO");
        venda2.adicionarItem(produto3, 50);
        venda2.adicionarItem(produto4, 10);
        vendaDAO.inserirVenda(venda2);

        Venda venda3 = new Venda("V3", cliente2, "PIX");
        venda3.adicionarItem(produto4, 5);
        vendaDAO.inserirVenda(venda3);

    }

    @AfterEach
    void tearDown() {
        vendaDAO.deletarTabelasVendas();
    }

    @Test
    public void testBuscarVendaId(){
        Venda buscada = vendaDAO.buscarVendaId("V1");
        System.out.println(buscada);
    }

    @Test
    public void testListarVendas(){
        for (Venda v : vendaDAO.listarVendas()){
            System.out.println();
            System.out.println(v);
        }
    }

    @Test
    public void testRemoverVenda(){
        vendaDAO.removerVenda("V3");
        Venda buscada = vendaDAO.buscarVendaId("V3");
        assertNull(buscada);
    }
}
