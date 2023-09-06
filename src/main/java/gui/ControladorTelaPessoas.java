package gui;

import control.SistemaAmigoSecreto;
import control.models.Pessoa;
import control.models.Presente;
import exceptions.ApelidoJaCadastradoException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ScreenManager;

public class ControladorTelaPessoas {

    @FXML
    private ChoiceBox<String> cbPessoa;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfApelido;

    @FXML
    private TextField tfNome;

    @FXML
    private TableColumn<Pessoa, String> tvColPresentesPessoa;

    @FXML
    private TableColumn<Presente, String> tvColTodosPresentes;

    @FXML
    private TableView<Pessoa> tvPresentesPessoa;

    @FXML
    private TableView<Presente> tvTodosPresentes;

    @FXML
    void buttonPesquisarOnClick(ActionEvent event) {

    }

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().getControladorTelaGrupos().atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaGruposScene(),
                "Grupos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPresentesScene(),
                "Presentes");
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().getControladorTelaSorteio().initialize();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaSorteioScene(), "Sorteio");
    }

    @FXML
    void buttonSalvarOnClick(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Preencha os campos corretamente para continuar",
                    "Tente novamente");
            this.tfNome.requestFocus();
        } else {
            try {
                Pessoa pessoa = new Pessoa(tfNome.getText(), tfApelido.getText(), pfSenha.getText());
                SistemaAmigoSecreto.getInstance().cadastrarPessoa(pessoa);
                showInfoAlert("Operação bem-sucedida", "A pessoa foi cadastrada",
                        "O apelido usado é: " + tfApelido.getText());
                clearFields();
            } catch (ApelidoJaCadastradoException e) {
                showErrorAlert("Erro: apelido já está cadastrado", e.getMessage(), "Tente outro apelido.");
                this.tfApelido.setText("");
                this.tfApelido.requestFocus();
            }
        }
    }

    @FXML
    void buttonCancelarOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    public void initialize() {
        tvColTodosPresentes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        //tvColPresentesPessoa.setCellValueFactory(cellData -> new SimpleStringProperty(presente -> cellData.getValue().getPresentesDesejados()));
    }

    private boolean camposEstaoVazios() {
        return pfSenha.getText().isBlank() || tfApelido.getText().isBlank() || tfNome.getText().isBlank();
    }

    private void showErrorAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String titulo, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        pfSenha.setText("");
        tfNome.setText("");
        tfApelido.setText("");
    }

}
