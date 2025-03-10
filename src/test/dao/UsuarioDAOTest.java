//package test.dao;
//
//import dao.ConexaoDB;
//import model.Usuario;
//import dao.UsuarioDAO;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UsuarioDAOTest {
////    private UsuarioDAO usuarioDAO;
////
////    @BeforeEach
////    public void setUp() throws SQLException {
////        Connection connection = ConexaoDB.getConnection();
////        usuarioDAO = new UsuarioDAO(connection);
////        usuarioDAO.criarTabela();
////        usuarioDAO.remover("1");
////        inserirUsuarios();
////    }
////
////    @AfterEach
////    void tearDown() {
////        usuarioDAO.deletarTabela();
////    }
////
////    void inserirUsuarios() {
////        Usuario usuario1 = new Usuario( "Gabriel", "senha123", true);
////        usuario1.setId("1");
////        usuarioDAO.inserir(usuario1);
////        Usuario usuario2 = new Usuario("2", "Maria", "senha@321", false);
////        usuarioDAO.inserir(usuario2);
////        Usuario usuario3 = new Usuario("3", "Pedro", "senha_951", false);
////        usuarioDAO.inserir(usuario3);
////
////        Usuario usuarioBuscado = usuarioDAO.buscarPorId("1");
////        assertNotNull(usuarioBuscado);
////        assertEquals("Gabriel", usuarioBuscado.getNome());
////        assertEquals("senha123", usuarioBuscado.getSenha());
////        assertTrue(usuarioBuscado.isAdm());
////    }
////
////    @Test
////    void testListarUsuarios(){
////        for(Usuario u : usuarioDAO.listarTodos()){
////            System.out.println(u.getNome());
////        }
////    }
////
////    @Test
////    void testBuscarNomeSenha(){
////        Usuario buscado = usuarioDAO.buscarNomeSenha("Gabriel", "senha123");
////        assertNotNull(buscado);
////        System.out.println(buscado);
////    }
////
////    @Test
////    void testRemoverUsuario(){
////
////        for(Usuario u : usuarioDAO.listarTodos()){
////            System.out.println(u.getNome());
////        }
////
////        usuarioDAO.remover("2");
////
////        for(Usuario u : usuarioDAO.listarTodos()){
////            System.out.println(u.getNome());
////        }
////
////    }
////
////    @Test
////    void testAtualizarUsuario(){
////        Usuario buscado = usuarioDAO.buscarPorId("2");
////        System.out.println(buscado.getNome());
////        System.out.println("Senha = " + buscado.getSenha());
////        System.out.println("Adm = " + buscado.isAdm());
////
////        buscado.setAdm(true);
////        usuarioDAO.atualizar(buscado);
////
////        Usuario atualizado = usuarioDAO.buscarPorId("2");
////        System.out.println(atualizado.getNome());
////        System.out.println("Senha = " + atualizado.getSenha());
////        System.out.println("Adm = " + atualizado.isAdm());
////    }
//
//}
