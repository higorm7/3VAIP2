package gui;

import control.SistemaAmigoSecreto;
import control.models.AmigosSecretos;
import control.models.Grupo;
import control.models.Pessoa;
import exceptions.GrupoJaSorteadoException;
import exceptions.GrupoNaoContemPessoasSuficientesException;
import exceptions.GrupoNaoFoiSorteadoException;
import exceptions.SenhaIncorretaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import view.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class ControladorTelaSorteio {

    @FXML
    private ChoiceBox<String> cbGrupo;

    @FXML
    private ChoiceBox<String> cbGrupoSorteio;

    @FXML
    private ChoiceBox<String> cbPessoa;

    @FXML
    private PasswordField pfSenha;

    @FXML
    void buttonConsultarOn(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Preencha os campos adequadamente",
                    "Tente novamente");
            cbGrupo.requestFocus();
        } else {
            try {
                Pessoa pessoa = SistemaAmigoSecreto.getInstance().obterPessoaDeApelido(cbPessoa.getValue());

                if (SistemaAmigoSecreto.getInstance().validarAcessoDe(pessoa, pfSenha.getText())) {
                    Pessoa amigoSecreto = SistemaAmigoSecreto.getInstance().obterAmigoSecretoDe(pessoa.getApelido(),
                            SistemaAmigoSecreto.getInstance().obterGrupoDeNome(cbGrupo.getValue()));
                    showInfoAlert("Acesso permitido", "O seu amigo secreto é " + amigoSecreto.getApelido(),
                            "Obrigado por jogar");
                    clearFields();
                }
            } catch (SenhaIncorretaException e) {
                showErrorAlert("Erro: senha incorreta", e.getMessage(), "Tente novamente");
                pfSenha.requestFocus();
            } catch (GrupoNaoFoiSorteadoException e) {
                showErrorAlert("Erro: grupo não sorteado", e.getMessage(), "Realize o sorteio do grupo");
                clearFields();
            }
        }
    }

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaGruposScene(), "Grupos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPessoasScene(), "Pessoas");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPresentesScene(), "Presentes");
    }

    @FXML
    void buttonRealizarSorteioOn(ActionEvent event) {
        if (cbGrupoSorteio.getValue() != null) {
            try {
                SistemaAmigoSecreto.getInstance().sortear(SistemaAmigoSecreto.getInstance().
                        obterGrupoDeNome(cbGrupoSorteio.getValue()));
                System.out.println("O resultado do sorteio é: ");
                for (AmigosSecretos amigos : SistemaAmigoSecreto.getInstance().
                        obterGrupoDeNome(cbGrupoSorteio.getValue()).getAmigosSecretos()) {
                    System.out.println(amigos);
                }

                showInfoAlert("Sorteio realizado", "O sorteio foi realizado com sucesso",
                        "Consulte o seu amigo secreto ao lado");
            } catch (GrupoJaSorteadoException e) {
                showErrorAlert("Erro: grupo já foi sorteado", e.getMessage(), "Tente outro grupo");
            } catch (GrupoNaoContemPessoasSuficientesException e) {
                showErrorAlert("Erro: pessoas insuficientes", e.getMessage(),
                        "Tente novamente quando cadastrar mais pessoas");
            }
        } else {
            showErrorAlert("Erro: selecione um grupo", "Selecione um grupo para sortear",
                    "Tente novamente");
        }
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void cbPessoaOnMousePressed(MouseEvent event) {
        if (cbGrupo.getValue() == null) {
            showErrorAlert("Erro: grupo vazio", "Selecione um grupo na caixa acima",
                    "Tente novamente");
            cbGrupo.requestFocus();
        } else {
            List<Pessoa> pessoasDoGrupo = new ArrayList<>(SistemaAmigoSecreto.getInstance().
                    obterGrupoDeNome(cbGrupo.getValue()).getParticipantes());
            for (Pessoa pessoa : pessoasDoGrupo) {
                cbPessoa.getItems().add(pessoa.getApelido());
            }
        }
    }

    public void initialize() {
        cbGrupo.getItems().clear();
        cbGrupoSorteio.getItems().clear();
        cbPessoa.getItems().clear();

        List<String> nomesGrupos = new ArrayList<>(0);
        for (Grupo grupo : SistemaAmigoSecreto.getInstance().obterGrupos()) {
            nomesGrupos.add(grupo.getNome());
        }

        cbGrupo.getItems().addAll(nomesGrupos);
        cbGrupoSorteio.getItems().addAll(nomesGrupos);
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

    private boolean camposEstaoVazios() {
        return pfSenha.getText().isBlank() || cbPessoa.getValue() == null || cbPessoa.getValue() == null;
    }

    private void clearFields() {
        this.cbGrupo.setValue(null);
        this.cbPessoa.setValue(null);
        this.pfSenha.setText("");

        this.initialize();
    }

}
