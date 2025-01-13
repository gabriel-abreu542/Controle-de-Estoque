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
    @FXML
    private Spinner<Double> precoUnit;
    @FXML
    private ChoiceBox<String> formaPagamento;
    @FXML
    private Label nomeUsuario;
    private CadastroCompra cadastroCompra;
    private Compra novaCompra;
    ArrayList<ItemTransacao> itensCompra;
    private ObservableList<String> optionsF;
    private ObservableList<String> optionsP;

    public void initialize() throws SQLException {
        quantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        quantidade.setEditable(true);
        precoUnit.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01, 1000.00, 1.00){
        });
        precoUnit.setEditable(true);
        CadastroFornecedores cadastroFornecedores = new CadastroFornecedores();
        optionsF = FXCollections.observableArrayList(cadastroFornecedores.listarNomes());
        listaFornecedores.setItems(optionsF);
        listaFornecedores.setVisibleRowCount(6);
        CadastroProduto cadastroProduto = new CadastroProduto();
        optionsP = FXCollections.observableArrayList(cadastroProduto.listarNomes());
        listaProdutos.setItems(optionsP);
        setupAutoCompleteComboBox(listaFornecedores, optionsF);
        setupAutoCompleteComboBox(listaProdutos, optionsP);
        listaFornecedores.setVisibleRowCount(6);
        listaProdutos.setVisibleRowCount(6);
        cadastroCompra = new CadastroCompra();
        ArrayList<String> opcoesP = new ArrayList<>();
        opcoesP.add("CREDITO");
        opcoesP.add("DEBITO");
        opcoesP.add("DINHEIRO");
        opcoesP.add("PIX");
        ObservableList<String> opcoesPag = FXCollections.observableArrayList(opcoesP);
        formaPagamento.setItems(opcoesPag);

        itensCompra = new ArrayList<ItemTransacao>();
    }

    void setupAutoCompleteComboBox(ComboBox<String> comboBox, ObservableList<String> listaOriginal){
        comboBox.setEditable(true);
        comboBox.getEditor().setOnKeyReleased(event -> {
            String input = comboBox.getEditor().getText();
            if(!input.isEmpty()){
                ObservableList<String> filtrados = FXCollections.observableArrayList();
                for(String item : comboBox.getItems()){
                    if(item.toLowerCase().startsWith(input.toLowerCase())){
                        filtrados.add(item);
                    }
                }
                comboBox.setItems(filtrados);

                if (!filtrados.isEmpty()) {
                    comboBox.show();
                    comboBox.setValue(filtrados.get(0));
                }
            }
            else{
                comboBox.setItems(listaOriginal);
            }
        });
    }

    @FXML
    void mostrarFornecedores(ActionEvent event) {
        if (!vBoxCarrinho.getChildren().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Não é possível alterar o fornecedor");
            alert.setContentText("Para alterar o fornecedor, o carrinho de compras deve estar vazio.");
            alert.showAndWait();
        } else {
            listaFornecedores.setDisable(false);
        }
    }

    @FXML
    void onAdicionarAction(ActionEvent event) throws SQLException {
        try{
            CadastroProduto cadastroProduto = new CadastroProduto();
            if (listaProdutos.getValue() == null) {
                Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
                alertaProdutos.setTitle("Aviso");
                alertaProdutos.setHeaderText("Campo 'Produto' inválido");
                alertaProdutos.setContentText("Escolha um produto para ser adicionado ao carrinho.");
                alertaProdutos.showAndWait();
            } else if (listaFornecedores.getValue() == null) {
                Alert alertaFornecedor = new Alert(Alert.AlertType.WARNING);
                alertaFornecedor.setTitle("Aviso");
                alertaFornecedor.setHeaderText("Campo 'Fornecedor' inválido");
                alertaFornecedor.setContentText("Escolha o fornecedor que está vendendo os produtos.");
                alertaFornecedor.showAndWait();
            } else {

                Produto adicionado = cadastroProduto.buscarPorNome(listaProdutos.getValue());

                String novoItem = quantidade.getValue() + " " + adicionado.getNome() + " " + precoUnit.getValue().floatValue() + " " + adicionado.getTipo();
                HBox container = new HBox();
                Label label = new Label(novoItem);
                Button botaoRemover = new Button("X");
                botaoRemover.resize(1.0, 1.0);
                botaoRemover.setOnAction(e -> {
                    vBoxCarrinho.getChildren().remove(container);
                    //itensCompra.remove();
                });
                ItemTransacao item = new ItemTransacao(adicionado, quantidade.getValue(), precoUnit.getValue().floatValue());
                itensCompra.add(item);

                container.getChildren().addAll(label, botaoRemover);
                vBoxCarrinho.getChildren().add(container);
                carrinho.setContent(vBoxCarrinho);
            }
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    void onNovoFornecedorAction(ActionEvent event) throws IOException {
        if(vBoxCarrinho.getChildren().isEmpty()){
            fecharJanela(botaoNovoFornecedor);
            novoLayout("/NovoFornecedor.fxml", "Novo Produto");
        }
        System.out.println("Compra cadastrada deve possuir apenas um fornecedor");
        //mostrar mensagem na tela usando Alert

    }

    @FXML
    void onNovoProdutoAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovoProduto);
        novoLayout("/NovoProduto.fxml", "Novo Produto");
    }

    @FXML
    void onFinalizarCompraAction(ActionEvent event) throws SQLException {
        String fornecedor = listaFornecedores.getValue();
        String formaP = formaPagamento.getValue();
        if(formaP == null){
            Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
            alertaProdutos.setTitle("Aviso");
            alertaProdutos.setHeaderText("Campo 'Forma de Pagamento' inválido");
            alertaProdutos.setContentText("Escolha uma forma de pagamento.");
            alertaProdutos.showAndWait();
        }
        else if(vBoxCarrinho.getChildren().isEmpty()) {
            Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
            alertaProdutos.setTitle("Aviso");
            alertaProdutos.setHeaderText("Carrinho vazio");
            alertaProdutos.setContentText("Insira pelo menos um produto no carrinho para completar a compra.");
            alertaProdutos.showAndWait();
        }
        else if(fornecedor == null){
            Alert alertaFornecedor = new Alert(Alert.AlertType.WARNING);
            alertaFornecedor.setTitle("Aviso");
            alertaFornecedor.setHeaderText("Campo 'Fornecedor' inválido");
            alertaFornecedor.setContentText("Escolha o fornecedor que está vendendo os produtos.");
            alertaFornecedor.showAndWait();
        }
        else{
            novaCompra = cadastroCompra.criarCompra(fornecedor,formaP, itensCompra);
            System.out.println("Compra realizada com sucesso!");
            System.out.println(novaCompra);
            
        }
    }

}
