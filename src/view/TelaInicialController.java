package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class TelaInicialController extends JanelaUsuarioController {
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
    private Button botaoFornecedores;

    @FXML
    private Button botaoProdutos;

    @FXML
    private Button botaoSair;

    @FXML
    void onNovaCompraAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovaCompra);
        novoLayout("/NovaCompra.fxml", "Nova Compra", getNomeUsuario());
    }

    @FXML
    void onNovaVendaAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovaVenda);
        FXMLLoader loader = novoLayout("/NovaVenda.fxml", "Nova Venda");
    }

    @FXML
    void onFornecedoresAction(ActionEvent event) throws IOException {
        fecharJanela(botaoFornecedores);
        FXMLLoader loader = novoLayout("/Fornecedores.fxml", "Fornecedores");
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
    public void onProdutosAction(ActionEvent actionEvent) {
    }
}
