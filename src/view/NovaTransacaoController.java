package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ItemTransacao;
import model.Produto;
import service.CadastroProduto;
import service.CadastroTransacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class NovaTransacaoController extends JanelaUsuarioController{
    @FXML
    protected Button botaoFinalizar;
    @FXML
    protected Button botaoCancelar;
    @FXML
    protected ScrollPane carrinho;
    @FXML
    protected Button botaoNovoProduto;
    ArrayList<ItemTransacao> itensTransacao;
    @FXML
    protected Label nomeUsuario;
    @FXML
    protected ComboBox<String> listaProdutos;
    protected ObservableList<String> optionsP;
    protected CadastroTransacao cadastroTransacao;
    protected CadastroProduto cadastroProduto;
    @FXML
    protected Spinner<Integer> quantidade;
    @FXML
    protected Spinner<Double> precoUnit;
    @FXML
    protected ChoiceBox<String> formaPagamento;
    @FXML
    protected Label labelTotal;
    protected float total;

    abstract CadastroTransacao setCadastroT() throws SQLException; // cria uma classe CadastroCompra ou CadastroVenda

    void inicializacaoJanela() throws SQLException {
        quantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        quantidade.setEditable(true);
        precoUnit.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01, 1000.00, 1.00){
        });
        precoUnit.setEditable(true);
        cadastroProduto = new CadastroProduto();
        optionsP = FXCollections.observableArrayList(cadastroProduto.listarNomes());
        listaProdutos.setItems(optionsP);
        setupAutoCompleteComboBox(listaProdutos, optionsP);
        listaProdutos.setVisibleRowCount(6);
        cadastroTransacao = setCadastroT();
        setUpFormasPagamento();
        itensTransacao = new ArrayList<ItemTransacao>();
        ArrayList<Produto> produtos;
        total = 0;
    }

    public abstract VBox getVBoxCarrinho();

    void setUpFormasPagamento(){
        ArrayList<String> opcoesP = new ArrayList<>();
        opcoesP.add("CREDITO");
        opcoesP.add("DEBITO");
        opcoesP.add("DINHEIRO");
        opcoesP.add("PIX");
        ObservableList<String> opcoesPag = FXCollections.observableArrayList(opcoesP);
        formaPagamento.setItems(opcoesPag);
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
    void onCancelarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoCancelar);
        novoLayout("/TelaInicial.fxml", "TelaInicial");
    }

    @FXML
    void onNovoProdutoAction(ActionEvent event) throws IOException {
        fecharJanela(botaoNovoProduto);
        novoLayout("/NovoProduto.fxml", "Novo Produto");
    }

    public abstract boolean camposPreenchidos();

    public boolean carrinhoVazio(){
        VBox vBoxCarrinho = getVBoxCarrinho();
        if(vBoxCarrinho.getChildren().isEmpty()) {
            Alert alertaProdutos = new Alert(Alert.AlertType.WARNING);
            alertaProdutos.setTitle("Aviso");
            alertaProdutos.setHeaderText("Carrinho vazio");
            alertaProdutos.setContentText("Insira pelo menos um produto no carrinho para completar a compra.");
            alertaProdutos.showAndWait();
            return true;
        }
        return false;
    }

    public abstract void confirmarTransacao() throws SQLException;

    @FXML
    void onAdicionarAction(ActionEvent event) throws SQLException {
        VBox vBoxCarrinho = getVBoxCarrinho();
        try{
            if(camposPreenchidos()){
                Produto adicionado = cadastroProduto.buscarPorNome(listaProdutos.getValue());
                ItemTransacao item = new ItemTransacao(adicionado, quantidade.getValue(), precoUnit.getValue().floatValue());
                HBox container = new HBox();
                Label quant = new Label(String.valueOf(quantidade.getValue()));
                Label nome = new Label(adicionado.getNome());
                nome.setLayoutX(20);
                Label precoU = new Label("R$" + precoUnit.getValue().floatValue());
                Label subTotal = new Label("R$" + String.format("%.2f",item.getSomaParcial()) );
                Button botaoRemover = new Button("X");

                botaoRemover.setOnAction(e -> {
                    vBoxCarrinho.getChildren().remove(container);
                    itensTransacao.remove(item);

                    total -= item.getSomaParcial();
                    String atualizado = total == 0? "0,00" : String.format("%.2f",total);
                    labelTotal.setText("R$" + atualizado);
                });

                itensTransacao.add(item);

                total += item.getSomaParcial();
                labelTotal.setText("R$" + String.format("%.2f",total));
                container.getChildren().addAll(quant, nome, precoU, subTotal, botaoRemover);
                container.setLayoutX(30);
                container.setSpacing(70);
                vBoxCarrinho.getChildren().add(container);
                carrinho.setContent(vBoxCarrinho);
            }
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    void onFinalizarTransacaoAction(ActionEvent event) throws SQLException, IOException{
        System.out.println("FinalizarTransacaoAction");
        if(camposPreenchidos() && !carrinhoVazio()){
            confirmarTransacao();
            fecharJanela(botaoFinalizar);
            novoLayout("/TelaInicial.fxml", "Tela Inicial");
        }
    }

}
