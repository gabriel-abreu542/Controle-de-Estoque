package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController extends Janela{
    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoHistoricoCompras;

    @FXML
    private Button botaoHistoricoVendas;

    @FXML
    private Button botaoNovaCompra;

    @FXML
    private Button botaoNovaVenda;

    @FXML
    private Button botaoProdutos;

    @FXML
    private Button botaoSair;

    @FXML
    private Label nomeUsuario;

    public void setNomeUsuario(String nome) {
        nomeUsuario.setText(nome);
    }

    @FXML
    void onNovaCompraAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovaCompra);
        novoLayout("/NovaCompra.fxml", "Nova Compra");
    }

    @FXML
    void OnSairAction(ActionEvent event) {
        try {
            fecharJanela(botaoSair);

            novoLayout("/Login.fxml", "Login");
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }

}
