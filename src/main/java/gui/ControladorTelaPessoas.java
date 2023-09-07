package gui;

import control.SistemaAmigoSecreto;
import control.models.Pessoa;
import control.models.Presente;
import exceptions.ApelidoJaCadastradoException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ScreenManager;

import java.util.ArrayList;
import java.util.List;

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
    private TableColumn<Presente, String> tvColPresentesPessoa;

    @FXML
    private TableColumn<Presente, String> tvColTodosPresentes;

    @FXML
    private TableView<Presente> tvPresentesPessoa;

    @FXML
    private TableView<Presente> tvTodosPresentes;

    @FXML
    void buttonPesquisarOnClick(ActionEvent event) {
        if (cbPessoa.getValue() == null) {
            showErrorAlert("Erro: nenhuma pessoa selecionada", "Selecione uma pessoa para pesquisar",
                    "Tente novamente");
            cbPessoa.requestFocus();
        } else {
            Pessoa pessoa = SistemaAmigoSecreto.getInstance().obterPessoaDeApelido(cbPessoa.getValue());
            List<Presente> presentes = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPresentes());
            presentes.removeIf(presente -> pessoa.getPresentesDesejados().contains(presente));

            configurarTv(pessoa.getPresentesDesejados(), tvPresentesPessoa);
            configurarTv(presentes, tvTodosPresentes);
        }
    }

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        this.clearFields();
        atualizarApresentacao();
        ScreenManager.getInstance().getControladorTelaGrupos().atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaGruposScene(),
                "Grupos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        this.clearFields();
        atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {
        this.clearFields();
        atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPresentesScene(),
                "Presentes");
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        this.clearFields();
        atualizarApresentacao();
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
        atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPrincipalScene(),
                "Amigos secretos");
    }

    @FXML
    void buttonAddOnAction(ActionEvent event) {
        if (tvTodosPresentes.getSelectionModel().getSelectedItem() == null) {
            showErrorAlert("Erro: nenhum presente selecionado", "Selecione um presente para adicionar",
                    "Tente novamente");
            tvTodosPresentes.requestFocus();
        } else if (cbPessoa.getValue() == null) {
            showErrorAlert("Erro: pessoa não selecionada", "Selecione uma pessoa para adicionar presentes",
                    "Tente novamente");
            cbPessoa.requestFocus();
        } else {
            Pessoa pessoa = SistemaAmigoSecreto.getInstance().obterPessoaDeApelido(cbPessoa.getValue());
            SistemaAmigoSecreto.getInstance().adicionarPresenteNaListaDe(pessoa,
                    tvTodosPresentes.getSelectionModel().getSelectedItem());

            List<Presente> presentesTotal = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPresentes());
            presentesTotal.removeIf(presente -> pessoa.getPresentesDesejados().contains(presente));

            configurarTv(presentesTotal, tvTodosPresentes);
            configurarTv(pessoa.getPresentesDesejados(), tvPresentesPessoa);
        }
    }

    @FXML
    void buttonDelOnAction(ActionEvent event) {
        if (tvPresentesPessoa.getSelectionModel().getSelectedItem() == null) {
            showErrorAlert("Erro: nenhum presente selecionado", "Selecione um presente para remover",
                    "Tente novamente");
            tvPresentesPessoa.requestFocus();
        } else if (cbPessoa.getValue() == null) {
            showErrorAlert("Erro: nenhuma pessoa selecionada", "Selecione uma pessoa para remover presentes",
                    "Tente novamente");
            cbPessoa.requestFocus();
        } else {
            Pessoa pessoa = SistemaAmigoSecreto.getInstance().obterPessoaDeApelido(cbPessoa.getValue());
            pessoa.getPresentesDesejados().remove(tvPresentesPessoa.getSelectionModel().getSelectedItem());

            List<Presente> presentesTotal = new ArrayList<>(SistemaAmigoSecreto.getInstance().obterPresentes());
            presentesTotal.removeIf(presente -> pessoa.getPresentesDesejados().contains(presente));

            configurarTv(presentesTotal, tvTodosPresentes);
            configurarTv(pessoa.getPresentesDesejados(), tvPresentesPessoa);
        }
    }

    public void initialize() {
        tvColTodosPresentes.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescricao()));
        tvColPresentesPessoa.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescricao()));

        cbPessoa.getItems().clear();
        List<String> pessoas = new ArrayList<>(0);
        for (Pessoa pessoa : SistemaAmigoSecreto.getInstance().obterPessoas()) {
            pessoas.add(pessoa.getApelido());
        }

        cbPessoa.getItems().addAll(pessoas);
    }

    private void configurarTv(List<Presente> presentes, TableView<Presente> tableView) {
        ObservableList<Presente> presentesList = FXCollections.observableArrayList();
        presentesList.addAll(presentes);
        tableView.setItems(presentesList);
    }

    public void atualizarApresentacao() {
        clearFields();
        configurarTv(SistemaAmigoSecreto.getInstance().obterPresentes(), this.tvTodosPresentes);
        tvPresentesPessoa.getItems().clear();
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

        cbPessoa.getItems().clear();
        List<String> pessoas = new ArrayList<>(0);
        for (Pessoa pessoa : SistemaAmigoSecreto.getInstance().obterPessoas()) {
            pessoas.add(pessoa.getApelido());
        }

        cbPessoa.getItems().addAll(pessoas);
    }

}
