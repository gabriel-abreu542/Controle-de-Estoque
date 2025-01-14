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

public class NovoFornecedorController extends JanelaController {

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
    void onAdicionarAction(ActionEvent event) throws IOException {
        Fornecedor f = cadastroFornecedores.criarFornecedor(
                cnpjFornecedor.getText(),
                nomeFornecedor.getText(),
                telefoneFornecedor.getText(),
                emailFornecedor.getText(),
                enderecoFornecedor.getText()
        );
        fecharJanela(botaoAdicionar);
        novoLayout("/NovaCompra.fxml", "Nova Compra");
    }

    @FXML
    void onVoltarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoVoltar);
        novoLayout("/NovaCompra.fxml", "Nova Compra");
    }

}
