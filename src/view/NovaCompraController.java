package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Compra;
import model.ItemTransacao;
import model.Produto;
import service.CadastroCompra;
import service.CadastroFornecedores;
import service.CadastroProduto;
import service.CadastroTransacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NovaCompraController extends NovaTransacaoController {
    @FXML
    private Button botaoFinalizar;
    @FXML
    private Button botaoCancelar;
    @FXML
    private Button botaoNovoFornecedor;
    @FXML
    private Button botaoNovoProduto;
    @FXML
    private ComboBox<String> listaFornecedores;
    private Compra novaCompra;
    private ObservableList<String> optionsF;


    public void initialize() throws SQLException {
        inicializacaoJanela();
        CadastroFornecedores cadastroFornecedores = new CadastroFornecedores();
        optionsF = FXCollections.observableArrayList(cadastroFornecedores.listarNomes());
        listaFornecedores.setItems(optionsF);
        setupAutoCompleteComboBox(listaFornecedores, optionsF);
        listaFornecedores.setVisibleRowCount(6);
    }

    @Override
    CadastroCompra setCadastroT() throws SQLException {
        return new CadastroCompra();
    }

    @FXML
    void mostrarFornecedores(ActionEvent event) {
        if (!vBoxCarrinho.getChildren().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Não é possível alterar o fornecedor");
            alert.setContentText("Compra cadastrada deve possuir apenas um fornecedor. esvazie o carrinho para alterar o fornecedor.");
            alert.showAndWait();
        } else {
            listaFornecedores.setDisable(false);
        }
    }

    @FXML
    void onNovoFornecedorAction(ActionEvent event) throws IOException {
        if(vBoxCarrinho.getChildren().isEmpty()){
            fecharJanela(botaoNovoFornecedor);
            novoLayout("/NovoFornecedor.fxml", "Novo Produto");
        }
        else {
            Alert alertaFornecedor = new Alert(Alert.AlertType.WARNING);
            alertaFornecedor.setTitle("Aviso");
            alertaFornecedor.setHeaderText("Itens já foram adicionados ao carrinho");
            alertaFornecedor.setContentText("Compra cadastrada deve possuir apenas um fornecedor. esvazie o carrinho para alterar o fornecedor");
            alertaFornecedor.showAndWait();
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
        } else if (listaFornecedores.getValue() == null) {
            Alert alertaFornecedor = new Alert(Alert.AlertType.WARNING);
            alertaFornecedor.setTitle("Aviso");
            alertaFornecedor.setHeaderText("Campo 'Fornecedor' inválido");
            alertaFornecedor.setContentText("Escolha o fornecedor que está vendendo os produtos.");
            alertaFornecedor.showAndWait();
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
        String fornecedor = listaFornecedores.getValue();
        String formaP = formaPagamento.getValue();

        novaCompra = ((CadastroCompra) cadastroTransacao).criarCompra(fornecedor,formaP, itensTransacao);
        Alert compraRealizada = new Alert(Alert.AlertType.INFORMATION);
        compraRealizada.setTitle("Compra realizada");
        compraRealizada.setHeaderText("Compra feita com o fornecedor " + fornecedor);
        compraRealizada.setContentText("Valor total: R$" + total + "\nForma de pagamento: " + formaP);
    }

}
