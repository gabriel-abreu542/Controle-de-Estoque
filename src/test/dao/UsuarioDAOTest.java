package test.dao;

import model.Usuario;
import dao.UsuarioDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = ConexaoDBTest.getConnection();

        try (Statement stmt = connection.createStatement()) {
            System.out.println("Query: " + stmt);
            stmt.execute("CREATE TABLE usuarios (id TEXT PRIMARY KEY, nome TEXT, senha TEXT, adm BOOLEAN)");
        }

        usuarioDAO = new UsuarioDAO(connection);
    }

    @AfterEach
    void tearDown() {
        try (Connection conn = ConexaoDBTest.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("Query: " + stmt);
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

    }


}
