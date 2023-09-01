module com.example.va3ip2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.va3ip2 to javafx.fxml;
    exports com.example.va3ip2;
}