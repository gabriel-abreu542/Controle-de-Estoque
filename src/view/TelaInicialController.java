package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaInicialController {
    @FXML
    private Label nomeUsuario;

    public void setNomeUsuario(String nome) {
        nomeUsuario.setText(nome);  // Alterando o texto da Label
    }
}
