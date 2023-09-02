package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControladorTelaPrincipal {

    @FXML
    public TabPane tab;

    @FXML
    void buttonOnClick(ActionEvent event) {
        this.tab.getTabs().add(new Tab("a"));
    }

}
