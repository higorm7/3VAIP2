package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.ScreenManager;

public class ControladorTelaPrincipal {

    @FXML
    void buttonGruposOnClick(ActionEvent event) {

    }

    @FXML
    void buttonPessoasOnClick(ActionEvent event) {
        ScreenManager.getInstance().changeScreen(ScreenManager.getInstance().getTelaPessoasScene(),
                "Pessoas");
    }

    @FXML
    void buttonPresentesOnClick(ActionEvent event) {

    }

    @FXML
    void buttonSorteioOnClick(ActionEvent event) {

    }

}

