package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Fornecedor;
import service.CadastroFornecedores;

import java.io.IOException;
import java.sql.SQLException;

public class NovoFornecedorController extends  Janela{

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField cnpjFornecedor;

    @FXML
    private TextField emailFornecedor;

    @FXML
    private TextField enderecoFornecedor;

    @FXML
    private TextField nomeFornecedor;

    @FXML
    private Label nomeUsuario;

    @FXML
    private TextField telefoneFornecedor;

    CadastroFornecedores cadastroFornecedores;

    public void initialize() throws SQLException {
        cadastroFornecedores = new CadastroFornecedores();
    }

    @FXML
    void onAdicionarAction(ActionEvent event) {
        Fornecedor f = cadastroFornecedores.criarFornecedor(
                nomeFornecedor.getText(),
                cnpjFornecedor.getText(),
                telefoneFornecedor.getText(),
                emailFornecedor.getText(),
                enderecoFornecedor.getText()
        );
        System.out.println("Adicionou novo fornecedor:");
        System.out.println(f);
    }

    @FXML
    void onVoltarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoVoltar);
        novoLayout("/NovaCompra.fxml", "Nova Compra");
    }

}
