package view;

import gui.ControladorTelaGrupos;
import gui.ControladorTelaPessoas;
import gui.ControladorTelaPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ScreenManager {

    private Stage primaryStage;

    private Scene telaPrincipalScene;
    private ControladorTelaPrincipal controladorTelaPrincipal;

    private Scene telaPessoasScene;
    private ControladorTelaPessoas controladorTelaPessoas;

    private Scene telaGruposScene;
    private ControladorTelaGrupos controladorTelaGrupos;

    private static ScreenManager instance;

    private ScreenManager() {
        this.initialize();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }

        return instance;
    }

    public Scene getTelaPrincipalScene() {
        return telaPrincipalScene;
    }

    public ControladorTelaPrincipal getControladorTelaPrincipal() {
        return controladorTelaPrincipal;
    }

    public Scene getTelaPessoasScene() {
        return telaPessoasScene;
    }

    public ControladorTelaPessoas getControladorTelaPessoas() {
        return controladorTelaPessoas;
    }

    public Scene getTelaGruposScene() {
        return telaGruposScene;
    }

    public ControladorTelaGrupos getControladorTelaGrupos() {
        return controladorTelaGrupos;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane telaPrincipalPane = fxmlLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/telaPrincipal.fxml")).openStream());
            this.telaPrincipalScene = new Scene(telaPrincipalPane);
            this.controladorTelaPrincipal = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            AnchorPane telaPessoasPane = fxmlLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/telaPessoas.fxml")).openStream());
            this.telaPessoasScene = new Scene(telaPessoasPane);
            this.controladorTelaPessoas = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            AnchorPane telaGruposPane = fxmlLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/telaGrupos.fxml")).openStream());
            this.telaGruposScene = new Scene(telaGruposPane);
            this.controladorTelaGrupos = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeScreen(Scene scene, String title) {
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle(title);
    }

}
