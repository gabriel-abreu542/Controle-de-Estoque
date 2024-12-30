package test.dao;

import dao.ClienteDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;
import model.Fornecedor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class FornecedorDAOTest {
    private FornecedorDAO fornecedorDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();
        fornecedorDAO = new FornecedorDAO(connection);
        fornecedorDAO.criarTabelaFornecedores();

        inserirFornecedores();
    }

    @AfterEach
    void tearDown(){
        fornecedorDAO.deletarTabelaFornecedores();
    }


    void inserirFornecedores() {
        Fornecedor fornecedor1 = new Fornecedor("12345", "Empresa A", "12349999", "contato@empresaA.br", "Rua A");
        fornecedorDAO.inserirFornecedor(fornecedor1);
        Fornecedor fornecedor2 = new Fornecedor("25431", "Empresa B", "1234321", "contato@empresaB.br", "Rua B");
        fornecedorDAO.inserirFornecedor(fornecedor2);
        Fornecedor fornecedor3 = new Fornecedor("32123", "Empresa C", "12349898", "contato@empresaC.br", "Rua C");
        fornecedorDAO.inserirFornecedor(fornecedor3);
    }

    @Test
    void testListarFornecedores(){
        for(Fornecedor u : fornecedorDAO.listarFornecedores()){
            System.out.println(u.getNome());
        }
    }

    @Test
    void testRemoverFornecedor(){

        for(Fornecedor fornecedor : fornecedorDAO.listarFornecedores()){
            System.out.println(fornecedor);
        }

        fornecedorDAO.removerFornecedor("32123");

        for(Fornecedor fornecedor : fornecedorDAO.listarFornecedores()){
            System.out.println(fornecedor);
        }

    }

    @Test
    void testAtualizarFornecedor(){
        Fornecedor buscado = fornecedorDAO.buscarFornecedorCNPJ("25431");
        System.out.println(buscado);

        buscado.setEndereco("Rua 123921390210");

        fornecedorDAO.atualizarFornecedor(buscado);

        Fornecedor atualizado = fornecedorDAO.buscarFornecedorCNPJ("25431");
        System.out.println(atualizado);
    }
}
