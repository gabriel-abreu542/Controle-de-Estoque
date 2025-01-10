//package test.dao;
//
//import dao.ProdutoDAO;
//import model.Produto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProdutoDAOTest {
//    private ProdutoDAO produtoDAO;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        Connection connection = ConexaoDBTest.getConnection();
//        produtoDAO = new ProdutoDAO(connection);
//        produtoDAO.criarTabela();
//        inserirProdutos();
//    }
//
//    @AfterEach
//    void tearDown() {
//        produtoDAO.deletarTabela();
//    }
//
//
//    void inserirProdutos() {
//        Produto produto1 = new Produto("1", "Martelo", 23.5f, 32.0f);
//        produto1.setDesc("Martelo de carpinteiro");
//        produto1.setTipo("Ferramenta");
//        produtoDAO.inserir(produto1);
//        Produto produto2 = new Produto("2", "Prego", 1.5f, 2.99f);
//        produtoDAO.inserir(produto2);
//        Produto produto3 = new Produto("3", "Capacete", 15.3f, 26.7f);
//        produtoDAO.inserir(produto3);
//
//        Produto produtoBuscado = produtoDAO.buscarPorId("1");
//        assertNotNull(produtoBuscado);
//        assertEquals("Martelo", produtoBuscado.getNome());
//        assertEquals(23.5f, produtoBuscado.getPrecoCompra());
//        assertEquals(32.0f, produtoBuscado.getPrecoVenda());
//        assertEquals("Martelo de carpinteiro", produtoBuscado.getDesc());
//        assertEquals("Ferramenta", produtoBuscado.getTipo());
//
//    }
//
//    @Test
//    void testListarProdutos(){
//        for(Produto u : produtoDAO.listarTodos()){
//            System.out.println(u);
//        }
//    }
//
//    @Test
//    void testRemoverProduto(){
//
//        for(Produto u : produtoDAO.listarTodos()){
//            System.out.println(u.getNome());
//        }
//
//        produtoDAO.remover("2");
//
//        for(Produto u : produtoDAO.listarTodos()){
//            System.out.println(u.getNome());
//        }
//
//    }
//
//    @Test
//    void testAtualizarProduto(){
//        Produto buscado = produtoDAO.buscarPorId("1");
//        System.out.println(buscado);
//
//        buscado.setPrecoVenda(39.99f);
//        produtoDAO.atualizar(buscado);
//
//        Produto atualizado = produtoDAO.buscarPorId("1");
//        System.out.println(atualizado);
//    }
//
//}
