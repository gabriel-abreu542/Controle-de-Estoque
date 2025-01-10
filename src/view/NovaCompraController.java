package view;

import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Compra;
import model.Fornecedor;
import model.Produto;
import service.CadastroCompra;
import service.CadastroFornecedores;
import service.CadastroProduto;

import java.awt.event.InputMethodEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NovaCompraController extends Janela{
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

    private Compra novaCompra;

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
    }

    @FXML
    void onAdicionarAction(ActionEvent event) throws SQLException {
        CadastroProduto cadastroProduto = new CadastroProduto();
        if(listaProdutos.getValue() != null) {
            Produto adicionado = cadastroProduto.buscarPorNome(listaProdutos.getValue());
            String novoItem = quantidade.getValue() + " " + adicionado.getNome() + " [preço]" + " " + adicionado.getTipo();
            HBox container = new HBox();
            Label label = new Label(novoItem);
            Button botaoRemover = new Button("X");
            botaoRemover.resize(1.0, 1.0);
            botaoRemover.setOnAction(e -> {
                vBoxCarrinho.getChildren().remove(container);
            });

            container.getChildren().addAll(label, botaoRemover);
            vBoxCarrinho.getChildren().add(container);
            carrinho.setContent(vBoxCarrinho);
        }
    }

    void onRemoverBotaoAction(){

    }

    @FXML
    void onNovoFornecedorAction(ActionEvent event) {

    }

    @FXML
    void onNovoProdutoAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovoProduto);
        novoLayout("NovoProduto.fxml", "Novo Produto");
    }

    @FXML
    void onFinalizarCompraAction(ActionEvent event) {

    }

}
