package test.dao;

import dao.FornecedorDAO;
import model.Fornecedor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FornecedorDAOTest {
    private FornecedorDAO fornecedorDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();
        fornecedorDAO = new FornecedorDAO(connection);
        fornecedorDAO.criarTabela();

        inserirFornecedores();
    }

    @AfterEach
    void tearDown(){
        fornecedorDAO.deletarTabela();
    }


    void inserirFornecedores() {
        Fornecedor fornecedor1 = new Fornecedor("12345", "Empresa A", "12349999", "contato@empresaA.br", "Rua A");
        fornecedorDAO.inserir(fornecedor1);
        Fornecedor fornecedor2 = new Fornecedor("25431", "Empresa B", "1234321", "contato@empresaB.br", "Rua B");
        fornecedorDAO.inserir(fornecedor2);
        Fornecedor fornecedor3 = new Fornecedor("32123", "Empresa C", "12349898", "contato@empresaC.br", "Rua C");
        fornecedorDAO.inserir(fornecedor3);
    }

    @Test
    void testListarFornecedores(){
        for(Fornecedor u : fornecedorDAO.listarTodos()){
            System.out.println(u.getNome());
        }
    }

    @Test
    void testRemoverFornecedor(){

        for(Fornecedor fornecedor : fornecedorDAO.listarTodos()){
            System.out.println(fornecedor);
        }

        fornecedorDAO.remover("32123");

        assertEquals(2, fornecedorDAO.listarTodos().size());
        assertNull(fornecedorDAO.buscarPorId("32123"));

    }

    @Test
    void testAtualizarFornecedor(){
        Fornecedor buscado = fornecedorDAO.buscarPorId("25431");
        assertEquals("Rua B", buscado.getEndereco());

        buscado.setEndereco("Rua 123921390210");

        fornecedorDAO.atualizar(buscado);

        Fornecedor atualizado = fornecedorDAO.buscarPorId("25431");
        assertEquals("Rua 123921390210",atualizado.getEndereco());

    }
}
