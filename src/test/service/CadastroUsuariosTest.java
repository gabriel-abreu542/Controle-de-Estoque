package test.service;

import model.Usuario;
import org.junit.jupiter.api.BeforeEach;

public class CadastroUsuariosTest {

    @BeforeEach
    public void setUp(){
        Usuario usuario1 = new Usuario("1", "Gabriel", "senha123", true);
    }
}
