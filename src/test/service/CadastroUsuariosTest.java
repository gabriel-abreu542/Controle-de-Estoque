package test.service;

import dao.ConexaoDB;
import dao.UsuarioDAO;
import model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CadastroUsuarios;
import test.dao.ConexaoDBTest;

import java.sql.SQLException;

public class CadastroUsuariosTest {
    private CadastroUsuarios cadastroUsuarios;
    Usuario usuario1;

    @BeforeEach
    public void setUp() {
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO(ConexaoDBTest.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        usuarioDAO.criarTabela();
        cadastroUsuarios = new CadastroUsuarios(usuarioDAO);
        usuario1 = new Usuario("1", "Gabriel", "senha123", true);
    }

    @Test
    public void testeInsercao(){
        cadastroUsuarios.adicionar(usuario1);
    }
}
