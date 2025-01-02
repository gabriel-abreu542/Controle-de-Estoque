package test.dao;

import dao.FornecedorDAO;
import dao.CompraDAO;
import dao.ProdutoDAO;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CompraDAOTest {
    private CompraDAO compraDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();
        compraDAO = new CompraDAO(connection);
        compraDAO.criarTabela();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        fornecedorDAO.criarTabela();
        produtoDAO.criarTabela();

        Fornecedor fornecedor1 = new Fornecedor("12345", "Empresa A", "12349999", "contato@empresaA.br", "Rua A");
        fornecedorDAO.inserir(fornecedor1);

        Produto produto1 = new Produto("P1","Parafuso", 0.50f, 1.00f);
        Produto produto2 = new Produto("P2","Cimento", 15.00f, 30.00f);
        produtoDAO.inserir(produto1);
        produtoDAO.inserir(produto2);

        Compra compra1 = new Compra("Compra1", fornecedor1, "DINHEIRO");
        compra1.adicionarItem(produto1, 30);
        compra1.adicionarItem(produto2, 3);
        compraDAO.inserir(compra1);

        Fornecedor fornecedor2 = new Fornecedor("25431", "Empresa B", "1234321", "contato@empresaB.br", "Rua B");
        fornecedorDAO.inserir(fornecedor2);

        Produto produto3 = new Produto("P3", "Tijolo", 2.00f, 3.50f);
        Produto produto4 = new Produto("P4", "Areia", 10.00f, 25.00f);
        produtoDAO.inserir(produto3);
        produtoDAO.inserir(produto4);

        Compra compra2 = new Compra("Compra2", fornecedor2, "CREDITO");
        compra2.adicionarItem(produto3, 50);
        compra2.adicionarItem(produto4, 10);
        compraDAO.inserir(compra2);

        Compra compra3 = new Compra("Compra3", fornecedor2, "PIX");
        compra3.adicionarItem(produto4, 5);
        compraDAO.inserir(compra3);

    }

    @AfterEach
    void tearDown() throws SQLException{
        Connection connection = ConexaoDBTest.getConnection();
        compraDAO.deletarTabela();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
        produtoDAO.deletarTabela();
        fornecedorDAO.deletarTabela();
    }

    @Test
    public void testBuscarCompraId(){
        Compra buscada = (Compra) compraDAO.buscarPorId("Compra1");
        System.out.println(buscada);
    }

    @Test
    public void testListarCompras(){
        for (Transacao v : compraDAO.listarTodos()){
            System.out.println();
            System.out.println(v);
        }
    }

    @Test
    public void testRemoverCompra(){
        compraDAO.remover("Compra3");
        Compra buscada = (Compra) compraDAO.buscarPorId("Compra3");
        assertNull(buscada);
    }
}
