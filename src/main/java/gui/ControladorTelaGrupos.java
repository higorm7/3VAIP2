package gui;

import control.SistemaAmigoSecreto;
import control.models.Grupo;
import control.models.Pessoa;
import exceptions.NomeDeGrupoJaCadastradoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import view.ScreenManager;

import java.util.List;

public class ControladorTelaGrupos {

    @FXML
    private DatePicker dpData;

    @FXML
    private TextField tfNome;

    @FXML
    void buttonCancelarOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPessoasScene(),
                "Pessoas");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {

    }

    @FXML
    void buttonSalvarOnClick(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Os campos necessários não podem estar vazios",
                    "Tente novamente");
        } else {
            try {
                Grupo grupo = new Grupo(tfNome.getText(), dpData.getValue());
                SistemaAmigoSecreto.getInstance().cadastrarGrupo(grupo);
                showInfoAlert("Grupo cadastrado", "Operação bem-sucedida",
                        "O nome do grupo é: " + tfNome.getText());
            } catch (NomeDeGrupoJaCadastradoException e) {
                showErrorAlert("Erro: nome de grupo já existe", e.getMessage(), "Tente outro nome");
            }
        }
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {

    }

    private boolean camposEstaoVazios() {
        return tfNome.getText().isBlank() || dpData.getValue() == null;
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
        tfNome.setText("");
        dpData.setValue(null);
    }

}
