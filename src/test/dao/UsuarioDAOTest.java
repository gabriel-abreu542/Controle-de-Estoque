package test.dao;

import model.Usuario;
import dao.UsuarioDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE usuarios (id TEXT PRIMARY KEY, nome TEXT, senha TEXT, adm BOOLEAN)");
        }

        usuarioDAO = new UsuarioDAO(connection);
    }

    @AfterEach
    void tearDown() {
        try (Connection conn = ConexaoDBTest.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE usuarios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInserirUsuario() {
        Usuario usuario = new Usuario("1", "Gabriel", "senha123", true);
        usuarioDAO.inserirUsuario(usuario);

        Usuario usuarioBuscado = usuarioDAO.buscarUsuarioId("1");
        assertNotNull(usuarioBuscado);
        assertEquals("Gabriel", usuarioBuscado.getNome());
        assertEquals("senha123", usuarioBuscado.getSenha());
        assertTrue(usuarioBuscado.isAdm());
    }

    @Test
    void testListarUsuarios(){
        Usuario usuario1 = new Usuario("1", "Gabriel", "senha123", true);
        usuarioDAO.inserirUsuario(usuario1);
        Usuario usuario2 = new Usuario("2", "Maria", "senha@321", false);
        usuarioDAO.inserirUsuario(usuario2);
        Usuario usuario3 = new Usuario("3", "Pedro", "senha_951", false);
        usuarioDAO.inserirUsuario(usuario3);

        Usuario usuario4 = usuarioDAO.buscarUsuarioId("1");
        System.out.println(usuario4.getNome());

        usuarioDAO.testeSQL();




    }


}
