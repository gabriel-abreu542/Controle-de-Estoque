package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class TelaInicialController extends JanelaController {
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
    void onNovaVendaAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovaVenda);
        novoLayout("/NovaVenda.fxml", "Nova Venda");
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

    @FXML
    public void onFornecedoresAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onProdutosAction(ActionEvent actionEvent) {
    }
}
