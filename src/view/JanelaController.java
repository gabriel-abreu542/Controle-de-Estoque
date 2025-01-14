package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class JanelaController {
    public void fecharJanela(Button botao){
        Stage stageAtual = (Stage) botao.getScene().getWindow();
        stageAtual.close();
    }

    public void novoLayout(String fxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        BorderPane novaPagina = loader.load();
        Stage novoStage = new Stage();
        Scene novaCena = new Scene(novaPagina);
        novoStage.setScene(novaCena);
        novoStage.setTitle(titulo);
        novoStage.show();
    }
}
