package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Usuario;
import service.CadastroUsuarios;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroController {
    private CadastroUsuarios cadastroUsuarios;
    @FXML
    private TextField confirmarSenha;
    @FXML
    private TextField nomeUsuario;
    @FXML
    private TextField senhaUsuario;
    @FXML
    private Button botaoLogin;

    public CadastroController() throws SQLException {
        cadastroUsuarios = new CadastroUsuarios();
    }

    @FXML
    void goToLogin(ActionEvent event) {
        try {
            // Fechando a janela atual
            Stage stageAtual = (Stage) botaoLogin.getScene().getWindow();
            stageAtual.close();

            // Carregando o novo layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            BorderPane novaPagina = loader.load();

            // Configurando o novo stage
            Stage novoStage = new Stage();
            Scene novaCena = new Scene(novaPagina);
            novoStage.setScene(novaCena);
            novoStage.setTitle("Nova Página");
            novoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCadastroAction(ActionEvent event) {
        if(senhaUsuario.getText().equals(confirmarSenha.getText())) {
            Usuario u = new Usuario(cadastroUsuarios.gerarNovoID(), nomeUsuario.getText(), senhaUsuario.getText(), true);
            cadastroUsuarios.adicionar(u);
        }
        else{
            System.out.println("Senha não confirmada!!!");
        }
    }

    @FXML
    void onLimparAction(ActionEvent event) {
        nomeUsuario.setText("");
        senhaUsuario.setText("");
    }

}
