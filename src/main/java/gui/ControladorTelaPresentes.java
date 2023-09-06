package gui;

import control.SistemaAmigoSecreto;
import control.models.Presente;
import exceptions.PresenteJaCadastradoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import view.ScreenManager;

public class ControladorTelaPresentes {

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfPreco;

    @FXML
    void buttonCancelarOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaGruposScene(),
                "Grupos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPessoasScene(),
                "Pessoas");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonSalvarOnClick(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Os campos informados não podem ser vazios.",
                    "Tente novamente");
            tfCategoria.requestFocus();
        } else {
            try {
                Presente presente = new Presente(tfCategoria.getText(), tfDescricao.getText(),
                        Double.parseDouble(tfPreco.getText()));
                SistemaAmigoSecreto.getInstance().cadastrarPresente(presente);
                showInfoAlert("Presente cadastrado", "Operação bem-sucedida",
                        "O presente " + presente.getDescricao() + " foi cadastrado com sucesso");
                clearFields();
            } catch (PresenteJaCadastradoException e) {
                showErrorAlert("Erro: presente já cadastrado", e.getMessage(), "Tente novamente");
                this.tfDescricao.setText("");
                this.tfDescricao.requestFocus();
            } catch (NumberFormatException e) {
                showErrorAlert("Erro: preço deve ser um número", "O preço deve ser numérico",
                        "Altere e tente novamente");
            }
        }
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaSorteioScene(), "Sorteio");
    }

    private boolean camposEstaoVazios() {
        return tfPreco.getText().isBlank() || tfDescricao.getText().isBlank() || tfCategoria.getText().isBlank();
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
        tfCategoria.setText("");
        tfDescricao.setText("");
        tfPreco.setText("");
    }

}
