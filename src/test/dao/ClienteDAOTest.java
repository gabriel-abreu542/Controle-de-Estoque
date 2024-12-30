package test.dao;

import dao.ClienteDAO;
import model.Cliente;
import model.Pagamento;
import model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDAOTest {
    private ClienteDAO clienteDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();
        clienteDAO = new ClienteDAO(connection);
        clienteDAO.criarTabelaClientes();

        inserirClientes();
    }

    @AfterEach
    void tearDown() {
        clienteDAO.deletarTabelaClientes();
    }


    void inserirClientes() {
        Cliente cliente1 = new Cliente("1", "Gabriel", "12349999");
        clienteDAO.inserirCliente(cliente1);
        Cliente cliente2 = new Cliente("2", "Maria", "1234321");
        cliente2.setDivida(200.0f);
        clienteDAO.inserirCliente(cliente2);
        Cliente cliente3 = new Cliente("3", "Pedro", "12349898");
        clienteDAO.inserirCliente(cliente3);
    }

    @Test
    void testListarClientes(){
        for(Cliente u : clienteDAO.listarClientes()){
            System.out.println(u.getNome());
        }
    }

    @Test
    void testRemoverCliente(){

        for(Cliente cliente : clienteDAO.listarClientes()){
            System.out.println(cliente);
        }

        clienteDAO.removerCliente("2");

        for(Cliente cliente : clienteDAO.listarClientes()){
            System.out.println(cliente);
        }

    }

    @Test
    void testAtualizarCliente(){
        Cliente buscado = clienteDAO.buscarClienteCPF("2");
        System.out.println(buscado);

        buscado.setDivida(0);
        clienteDAO.atualizarCliente(buscado);

        Cliente atualizado = clienteDAO.buscarClienteCPF("2");
        System.out.println(atualizado);
    }

}
