package test.service;

import dao.ConexaoDB;
import dao.UsuarioDAO;
import model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CadastroUsuarios;
import test.dao.ConexaoDBTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CadastroUsuariosTest {
    private CadastroUsuarios cadastroUsuarios;
    Usuario usuario1;

    @BeforeEach
    public void setUp() {
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO(ConexaoDB.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        usuarioDAO.criarTabela();
        try {
            cadastroUsuarios = new CadastroUsuarios();
            usuario1 = new Usuario("1", "Gabriel", "senha123", true);
            cadastroUsuarios.adicionar(usuario1);
        } catch (SQLException e) {
            System.out.println("Erro no setup de CadastroUsuariosTest");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testeInsercao(){
        Usuario u = cadastroUsuarios.buscarPorId("1");
        System.out.println(u);
    }

    @Test
    public void testeRemocao(){
        cadastroUsuarios.deletar("1");
        Usuario u = cadastroUsuarios.buscarPorId("1");
        assertNull(u);
    }
}
