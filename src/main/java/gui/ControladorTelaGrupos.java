package gui;

import control.SistemaAmigoSecreto;
import control.models.Grupo;
import control.models.Pessoa;
import exceptions.NomeDeGrupoJaCadastradoException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ScreenManager;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ControladorTelaGrupos {

    @FXML
    private ChoiceBox<String> cbGrupo;

    @FXML
    private DatePicker dpData;

    @FXML
    private TextField tfNome;

    @FXML
    private TableColumn<Pessoa, String> tvColPessoasGrupo;

    @FXML
    private TableColumn<Pessoa, String> tvColTodasPessoas;

    @FXML
    private TableView<Pessoa> tvPessoasGrupo;

    @FXML
    private TableView<Pessoa> tvTodasPessoas;

    @FXML
    void buttonCancelarCadastroOnClick(ActionEvent event) {
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
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPresentesScene(),
                "Presentes");
    }

    @FXML
    void buttonSalvarCadastroOnClick(ActionEvent event) {
        if (camposEstaoVazios()) {
            showErrorAlert("Erro: campos vazios", "Os campos necessários não podem estar vazios",
                    "Tente novamente");
            tfNome.requestFocus();
        } else {
            try {
                Grupo grupo = new Grupo(tfNome.getText(), dpData.getValue());
                SistemaAmigoSecreto.getInstance().cadastrarGrupo(grupo);
                showInfoAlert("Grupo cadastrado", "Operação bem-sucedida",
                        "O nome do grupo é: " + tfNome.getText());
                clearFields();
                atualizarApresentacao();
            } catch (NomeDeGrupoJaCadastradoException e) {
                showErrorAlert("Erro: nome de grupo já existe", e.getMessage(), "Tente outro nome");
                this.tfNome.setText("");
                this.tfNome.requestFocus();
            } catch (DateTimeParseException e) {
                showErrorAlert("Erro: data inválida", "Selecione uma data de sorteio adequada",
                        "Tente novammente");
                this.dpData.setValue(null);
                this.dpData.requestFocus();
            }
        }
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        this.clearFields();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaSorteioScene(), "Sorteio");
    }

    @FXML
    void buttonCancelarPessoasOnAction(ActionEvent event) {

    }
    @FXML
    void buttonSalvarPessoasOnAction(ActionEvent event) {

    }

    @FXML
    void buttonAddOnClick(ActionEvent event) {
        if (tvTodasPessoas.getSelectionModel().getSelectedItem() == null) {
            showErrorAlert("Erro: nenhuma pessoa selecionada", "Escolha pessoas para adicionar ao grupo",
                    "Tente novamente clicando na tabela.");
            tvTodasPessoas.requestFocus();
        } else if (cbGrupo.getValue() == null) {
            showErrorAlert("Erro: nenhum grupo selecionado", "Escolha um grupo para adicionar as pessoas",
                    "Tente novamente");
        } else {
            Grupo grupo = SistemaAmigoSecreto.getInstance().obterGrupoDeNome(cbGrupo.getValue());
            grupo.getParticipantes().add(tvTodasPessoas.getSelectionModel().getSelectedItem());
            List<Pessoa> pessoasTotal = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPessoas());
            pessoasTotal.removeIf(pessoa -> grupo.getParticipantes().contains(pessoa));

            configurarTv(pessoasTotal, tvTodasPessoas);
            configurarTv(grupo.getParticipantes(), tvPessoasGrupo);
        }
    }

    @FXML
    void buttonDelOnClick(ActionEvent event) {
        if (tvPessoasGrupo.getSelectionModel().getSelectedItem() == null) {
            showErrorAlert("Erro: nenhuma pessoa selecionada", "Escolha pessoas para remover do grupo",
                    "Tente novamente clicando na tabela de pessoas no grupo.");
            tvPessoasGrupo.requestFocus();
        } else if (cbGrupo.getValue() == null) {
            showErrorAlert("Erro: nenhum grupo selecionado", "Escolha um grupo para remover as pessoas",
                    "Tente novamente");
        } else {
            Grupo grupo = SistemaAmigoSecreto.getInstance().obterGrupoDeNome(cbGrupo.getValue());
            grupo.getParticipantes().remove(tvPessoasGrupo.getSelectionModel().getSelectedItem());
            List<Pessoa> pessoasTotal = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPessoas());
            pessoasTotal.removeIf(pessoa -> grupo.getParticipantes().contains(pessoa));

            configurarTv(pessoasTotal, tvTodasPessoas);
            configurarTv(grupo.getParticipantes(), tvPessoasGrupo);
        }
    }

    @FXML
    void buttonPesquisarOnClick(ActionEvent event) {
        if (cbGrupo.getValue() == null) {
            showErrorAlert("Erro: nenhum grupo escolhido", "Escolha um grupo para pesquisar",
                    "Tente novamente");
            cbGrupo.requestFocus();
        } else {
            Grupo grupo = SistemaAmigoSecreto.getInstance().obterGrupoDeNome(cbGrupo.getValue());
            List<Pessoa> pessoasTotal = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPessoas());
            pessoasTotal.removeIf(pessoa -> grupo.getParticipantes().contains(pessoa));

            configurarTv(grupo.getParticipantes(), tvPessoasGrupo);
            configurarTv(pessoasTotal, this.tvTodasPessoas);
        }
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

    public void initialize() {
        tvColTodasPessoas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApelido()));
        tvColPessoasGrupo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApelido()));
    }

    private void configurarTv(List<Pessoa> pessoas, TableView<Pessoa> tableView) {
        ObservableList<Pessoa> pessoaList = FXCollections.observableArrayList();
        pessoaList.addAll(pessoas);
        tableView.setItems(pessoaList);
    }

    public void atualizarApresentacao() {
        limparItens();
        configurarTv(SistemaAmigoSecreto.getInstance().obterPessoas(), this.tvTodasPessoas);
    }

    private void limparItens() {
        cbGrupo.getItems().clear();

        List<String> nomesGrupos = new ArrayList<>(0);
        for (Grupo grupo : SistemaAmigoSecreto.getInstance().obterGrupos()) {
            nomesGrupos.add(grupo.getNome());
        }

        cbGrupo.getItems().addAll(nomesGrupos);
        tvTodasPessoas.getItems().clear();
        tvPessoasGrupo.getItems().clear();
    }

}
