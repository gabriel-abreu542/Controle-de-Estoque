package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.CadastroUsuarios;
import model.Usuario;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends Janela {

    @FXML
    private TextField nomeUsuario;
    @FXML
    private PasswordField senhaUsuario;
    @FXML
    private Button botaoCadastro;
    @FXML
    private Button botaoLogin;

    private CadastroUsuarios cadastroUsuarios;

    public void initialize() throws SQLException{
        cadastroUsuarios = new CadastroUsuarios();
    }

    @FXML
    void onLimparAction(ActionEvent event) {
        nomeUsuario.setText("");
        senhaUsuario.setText("");
    }

    @FXML
    void onLoginAction(ActionEvent event) throws IOException {
        Usuario u = cadastroUsuarios.Login(nomeUsuario.getText(), senhaUsuario.getText());
        if(u != null){
            System.out.println("Cadastro deu certo! Usuário: ");
            System.out.println(u);

            try{
                // Fechando a janela atual
                Stage stageAtual = (Stage) botaoLogin.getScene().getWindow();
                stageAtual.close();

                // Carregando a tela inicial
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TelaInicial.fxml"));
                BorderPane root = loader.load();

                TelaInicialController telaInicialController = loader.getController();

                telaInicialController.setNomeUsuario(u.getNome());

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Erro ao abrir a tela inicial.");
                e.printStackTrace();
            }
        }
    }


    @FXML
    void goToCadastro(ActionEvent event) throws SQLException {
        try {
            // Fechando a janela atual
            Stage stageAtual = (Stage) botaoCadastro.getScene().getWindow();
            stageAtual.close();

            // Carregando o novo layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cadastro.fxml"));
            BorderPane novaPagina = loader.load();

            // Configurando o novo stage
            Stage novoStage = new Stage();
            Scene novaCena = new Scene(novaPagina);
            novoStage.setScene(novaCena);
            novoStage.setTitle("Nova Página");
            novoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
