//package test.dao;
//
//import dao.ClienteDAO;
//import model.Cliente;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class ClienteDAOTest {
//    private ClienteDAO clienteDAO;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        Connection connection = ConexaoDBTest.getConnection();
//
//        clienteDAO = new ClienteDAO(connection);
//        clienteDAO.criarTabela();
//
//        inserirClientes();
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        clienteDAO.deletarTabela();
//    }
//
//    void inserirClientes() {
//        System.out.println("\nNovo teste\n");
//        Cliente cliente1 = new Cliente("1", "Gabriel", "12349999");
//        clienteDAO.inserir(cliente1);
//        Cliente cliente2 = new Cliente("2", "Maria", "1234321");
//        cliente2.setDivida(200.0f);
//        clienteDAO.inserir(cliente2);
//        Cliente cliente3 = new Cliente("3", "Pedro", "12349898");
//        clienteDAO.inserir(cliente3);
//
//    }
//
//    @Test
//    void testListarClientes(){
//        for(Cliente u : clienteDAO.listarTodos()){
//            System.out.println(u.getNome());
//        }
//    }
//
//    @Test
//    void testRemoverCliente(){
//
//        for(Cliente cliente : clienteDAO.listarTodos()){
//            System.out.println(cliente);
//        }
//
//        clienteDAO.remover("2");
//
//        for(Cliente cliente : clienteDAO.listarTodos()){
//            System.out.println(cliente);
//        }
//
//    }
//
//    @Test
//    void testAtualizarCliente(){
//        Cliente buscado = clienteDAO.buscarPorId("2");
//        System.out.println(buscado);
//
//        buscado.setDivida(0);
//        clienteDAO.atualizar(buscado);
//
//        Cliente atualizado = clienteDAO.buscarPorId("2");
//        System.out.println(atualizado);
//    }
//
//}
