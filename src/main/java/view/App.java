package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(ScreenManager.getInstance().getTelaPrincipalScene());
        primaryStage.setTitle("Amigos secretos");
        primaryStage.setResizable(false);

        ScreenManager.getInstance().setPrimaryStage(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        App.launch(args);
    }
}