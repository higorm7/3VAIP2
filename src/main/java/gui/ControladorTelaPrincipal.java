package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.ScreenManager;

public class ControladorTelaPrincipal {

    @FXML
    void buttonGruposOnClick(ActionEvent event) {
        ScreenManager.getInstance().getControladorTelaGrupos().atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaGruposScene(),
                "Grupos");
    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        ScreenManager.getInstance().getControladorTelaPessoas().atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPessoasScene(),
                "Pessoas");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {
        ScreenManager.getInstance().getControladorTelaPessoas().atualizarApresentacao();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPresentesScene(),
                "Presentes");
    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {
        ScreenManager.getInstance().getControladorTelaSorteio().initialize();
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaSorteioScene(),
                "Sorteio");
    }

}

