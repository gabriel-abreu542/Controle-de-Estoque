package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Produto;
import service.CadastroProduto;

import java.io.IOException;
import java.sql.SQLException;

public class NovoProdutoController extends JanelaController {
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoVoltar;
    @FXML
    private TextField descProduto;
    @FXML
    private TextField nomeProduto;
    @FXML
    private Label nomeUsuario;
    @FXML
    private TextField tipoProduto;

    CadastroProduto cadastroProduto;

    public void initialize() throws SQLException {
        cadastroProduto = new CadastroProduto();
    }

    @FXML
    void onAdicionarAction(ActionEvent event) {
        Produto novoProduto = cadastroProduto.criarProduto(nomeProduto.getText(), descProduto.getText(), tipoProduto.getText());
        System.out.println("Adicionou: ");
        System.out.println(novoProduto);
    }

    @FXML
    void onVoltarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoVoltar);
        novoLayout("/NovaCompra.fxml", "Nova Compra");
    }
}

