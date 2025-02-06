package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaUsuarioController extends JanelaController{
    @FXML
    private Label nomeUsuario;

    public JanelaUsuarioController(){
        nomeUsuario = new Label();
    }

    public void novoLayout(String fxml, String titulo, String usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        BorderPane novaPagina = loader.load();
        JanelaUsuarioController novoController = loader.getController();
        novoController.setNomeUsuario(usuario);

        Stage novoStage = new Stage();
        Scene novaCena = new Scene(novaPagina);
        novoStage.setScene(novaCena);
        novoStage.setTitle(titulo);
        novoStage.show();


    }

    public void setNomeUsuario(String usuario) {
        nomeUsuario.setText(usuario);
    }

    public String getNomeUsuario() {
        return nomeUsuario.getText();
    }
}
