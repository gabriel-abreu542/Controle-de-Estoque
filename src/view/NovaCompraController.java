package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Fornecedor;
import model.Produto;
import service.CadastroCompra;
import service.CadastroFornecedores;
import service.CadastroProduto;

import java.awt.event.InputMethodEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class NovaCompraController extends Janela{
    CadastroCompra cadastroCompra;
    ArrayList<Fornecedor> fornecedores;
    ArrayList<Produto> produtos;
    @FXML
    private VBox vBoxCarrinho;
    @FXML
    private ScrollPane carrinho;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoNovoFornecedor;
    @FXML
    private Button botaoNovoProduto;
    @FXML
    private ComboBox<String> listaFornecedores;
    @FXML
    private ComboBox<String> listaProdutos;
    @FXML
    private Spinner<Integer> quantidade;
    @FXML
    private ChoiceBox<String> teste;
    public void initialize() throws SQLException {
        quantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        quantidade.setEditable(true);
        CadastroFornecedores cadastroFornecedores = new CadastroFornecedores();
        ObservableList<String> optionsF = FXCollections.observableArrayList(cadastroFornecedores.listarNomes());
        listaFornecedores.setItems(optionsF);
        listaFornecedores.setVisibleRowCount(6);
        CadastroProduto cadastroProduto = new CadastroProduto();
        ObservableList<String> optionsP = FXCollections.observableArrayList(cadastroProduto.listarNomes());
        listaProdutos.setItems(optionsP);
        listaProdutos.setVisibleRowCount(6);
        teste.setItems(optionsF);

    }

    @FXML
    void onAdicionarAction(ActionEvent event) {
        Label label = new Label("Testando");
        System.out.println(vBoxCarrinho
        );
    }

    @FXML
    void onNovoFornecedorAction(ActionEvent event) {

    }

    @FXML
    void onNovoProdutoAction(ActionEvent event) {

    }

    @FXML
    void onFinalizarCompraAction(ActionEvent event) {

    }

    @FXML
    void onDigitandoCaixaAction(InputMethodEvent event) {
        System.out.println("Teste: está digitando");
    }

}
