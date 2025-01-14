package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Venda;
import service.CadastroClientes;
import service.CadastroCompra;
import service.CadastroTransacao;
import service.CadastroVenda;

import java.io.IOException;
import java.sql.SQLException;

public class NovaVendaController extends NovaTransacaoController{

    @FXML
    private Button botaoNovoCliente;
    @FXML
    private ComboBox<String> listaClientes;
    private Venda novaVenda;
    private ObservableList<String> optionsC;

    public void initialize() throws SQLException {
        inicializacaoJanela();
        CadastroClientes cadastroClientes = new CadastroClientes();
        optionsC = FXCollections.observableArrayList(cadastroClientes.listarNomes());
        listaClientes.setItems(optionsC);
        setupAutoCompleteComboBox(listaClientes, optionsC);
        listaClientes.setVisibleRowCount(6);
    }

    @Override
    CadastroTransacao setCadastroT() throws SQLException {
        return new CadastroVenda();
    }

    @FXML
    void mostrarClientes(ActionEvent event) {
        if (!vBoxCarrinho.getChildren().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Não é possível alterar o cliente");
            alert.setContentText("Venda cadastrada deve possuir apenas um cliente. Esvazie o carrinho para alterar o Cliente.");
            alert.showAndWait();
        } else {
            listaClientes.setDisable(false);
        }
    }


    @FXML
    void onNovoClienteAction(ActionEvent event) throws IOException {
        if(vBoxCarrinho.getChildren().isEmpty()){
            fecharJanela(botaoNovoCliente);
            novoLayout("/NovoCliente.fxml", "Novo Produto");
        }
        else {
            Alert alertaCliente = new Alert(Alert.AlertType.WARNING);
            alertaCliente.setTitle("Aviso");
            alertaCliente.setHeaderText("Itens já foram adicionados ao carrinho");
            alertaCliente.setContentText("Venda cadastrada deve possuir apenas um fornecedor. esvazie o carrinho para alterar o fornecedor");
            alertaCliente.showAndWait();
        }
    }

    @Override
    public boolean camposPreenchidos() {
        if (listaProdutos.getValue() == null) {
            Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
            alertaProdutos.setTitle("Aviso");
            alertaProdutos.setHeaderText("Campo 'Produto' inválido");
            alertaProdutos.setContentText("Escolha um produto para ser adicionado ao carrinho.");
            alertaProdutos.showAndWait();
            return false;
        } else if (listaClientes.getValue() == null) {
            Alert alertaCliente = new Alert(Alert.AlertType.WARNING);
            alertaCliente.setTitle("Aviso");
            alertaCliente.setHeaderText("Campo 'Cliente' inválido");
            alertaCliente.setContentText("Escolha o cliente que está comprando os produtos.");
            alertaCliente.showAndWait();
            return false;
        } else if(formaPagamento.getValue() == null){
            Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
            alertaProdutos.setTitle("Aviso");
            alertaProdutos.setHeaderText("Campo 'Forma de Pagamento' inválido");
            alertaProdutos.setContentText("Escolha uma forma de pagamento.");
            alertaProdutos.showAndWait();
            return false;
        }

        return true;
    }

    @Override
    public void confirmarTransacao() throws SQLException {
        String cliente = listaClientes.getValue();
        String formaP = formaPagamento.getValue();

        novaVenda = ((CadastroVenda) cadastroTransacao).criarVenda(cliente,formaP, itensTransacao);
        Alert vendaRealizada = new Alert(Alert.AlertType.INFORMATION);
        vendaRealizada.setTitle("Venda realizada");
        vendaRealizada.setHeaderText("Venda feita com o cliente " + cliente);
        vendaRealizada.setContentText("Valor total: R$" + total + "\nForma de pagamento: " + formaP);
    }
}
