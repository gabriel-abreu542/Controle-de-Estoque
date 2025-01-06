package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.CadastroUsuarios;
import model.Usuario;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField nomeUsuario;
    @FXML
    private TextField senhaUsuario;
    @FXML
    private Button botaoCadastro;
    private CadastroUsuarios cadastroUsuarios;

    @FXML
    void onLimparAction(ActionEvent event) {
        nomeUsuario.setText("");
        senhaUsuario.setText("");
    }

    @FXML
    void onLoginAction(ActionEvent event) {
        Usuario u = cadastroUsuarios.Login(nomeUsuario.getText(), senhaUsuario.getText());
    }

    @FXML
    void goToCadastro(ActionEvent event) throws SQLException {
        try {
            // Fechando a janela atual
            Stage stageAtual = (Stage) botaoCadastro.getScene().getWindow();
            stageAtual.close();

            // Carregando o novo layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cadastro.fxml"));
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

}
