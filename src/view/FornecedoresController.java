package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Fornecedor;
import service.CadastroFornecedores;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.sql.SQLException;

public class FornecedoresController extends JanelaUsuarioController{
    @FXML
    private VBox VBoxFornecedores;

    @FXML
    private Button botaoCancelar;

    @FXML
    private ScrollPane listaFornecedores;

    public void initialize() throws SQLException {
        VBoxFornecedores = new VBox();
        CadastroFornecedores cadastroFornecedores = new CadastroFornecedores();
        HBox container;

        for(Fornecedor f: cadastroFornecedores.listarTodos()){
            System.out.println(f);
            container = new HBox();
            Label cnpj = new Label(f.getId());
            Label nome = new Label(f.getNome());
            Label email = new Label(f.getEmail());
            Label telefone = new Label(f.getTelefone());
            container.getChildren().addAll(cnpj, nome, email, telefone);
            container.setSpacing(80);
            VBoxFornecedores.getChildren().add(container);
        }
        listaFornecedores.setContent(VBoxFornecedores);
    }

    @FXML
    void onCancelarAction(ActionEvent event) throws IOException {
        fecharJanela(botaoCancelar);
        novoLayout("/TelaInicial.fxml", "TelaInicial");
    }

}
