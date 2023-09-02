module src.va3ip {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui to javafx.fxml;
    exports gui;

    opens view to javafx.fxml;
    exports view;
}