package gui;

import control.SistemaAmigoSecreto;
import control.models.Pessoa;
import exceptions.ApelidoJaCadastradoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorTelaPessoas {

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfApelido;

    @FXML
    private TextField tfNome;

    @FXML
    void buttonGruposOnClick(ActionEvent event) {

    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {

    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {

    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {

    }
    @FXML
    void buttonSalvarOnClick(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Preencha os campos corretamente para continuar",
                    "Tente novamente");
        } else {
            try {
                Pessoa pessoa = new Pessoa(tfNome.getText(), tfApelido.getText(), pfSenha.getText());
                SistemaAmigoSecreto.getInstance().cadastrarPessoa(pessoa);
                showInfoAlert("Operação bem-sucedida", "A pessoa foi cadastrada",
                        "O apelido usado é: " + tfApelido.getText());
            } catch (ApelidoJaCadastradoException e) {
                showErrorAlert("Erro: apelido já está cadastrado", "O apelido já está cadastrado",
                        "Tente outro apelido.");
            }
        }
    }

    private boolean camposEstaoVazios() {
        return pfSenha.getText().isBlank() && tfApelido.getText().isBlank() && tfNome.getText().isBlank();
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
