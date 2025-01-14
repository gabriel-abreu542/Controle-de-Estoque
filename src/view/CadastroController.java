package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;
import service.CadastroUsuarios;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroController extends JanelaController {
    private CadastroUsuarios cadastroUsuarios;
    @FXML
    private PasswordField confirmarSenha;
    @FXML
    private TextField nomeUsuario;
    @FXML
    private PasswordField senhaUsuario;
    @FXML
    private Button botaoLogin;
    @FXML
    private CheckBox usuarioAdm;

    public CadastroController() throws SQLException {
        cadastroUsuarios = new CadastroUsuarios();
    }

    @FXML
    void goToLogin(ActionEvent event) {
        try {
            fecharJanela(botaoLogin);

            novoLayout("/Login.fxml", "Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCadastroAction(ActionEvent event) {
        if(senhaUsuario.getText().equals(confirmarSenha.getText())) {
            try{
                Usuario cadastrado = cadastroUsuarios.criarUsuario(nomeUsuario.getText(), senhaUsuario.getText(), usuarioAdm.isSelected());
                System.out.println("Usuario cadastrado:\n" + cadastrado);
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO: " + e.getMessage());
            }

        }
        else{
            System.out.println("Senha não confirmada!!!");
        }
    }

    @FXML
    void onLimparAction(ActionEvent event) {
        nomeUsuario.setText("");
        senhaUsuario.setText("");
        confirmarSenha.setText("");
    }

}
