package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Fornecedor;
import service.CadastroClientes;
import service.CadastroFornecedores;

import java.io.IOException;
import java.sql.SQLException;

public class NovoClienteController extends JanelaController{
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoVoltar;
    @FXML
    private TextField cpfCliente;
    @FXML
    private TextField nomeCliente;
    @FXML
    private Label nomeUsuario;
    @FXML
    private TextField telefoneCliente;
    CadastroClientes cadastroClientes;

    public void initialize() throws SQLException {
        cadastroClientes = new CadastroClientes();
    }

    @FXML
    void onAdicionarAction(ActionEvent event) throws IOException {
        Cliente c = cadastroClientes.criarCliente(
                cpfCliente.getText(),
                nomeCliente.getText(),
                telefoneCliente.getText()
        );
        fecharJanela(botaoAdicionar);
        novoLayout("/NovaVenda.fxml", "Nova Venda");
        System.out.println("Adicionou novo cliente: ");
        System.out.println(c);
    }

    @FXML
    void onVoltarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoVoltar);
        novoLayout("/NovaVenda.fxml", "Nova Venda");
    }
}
