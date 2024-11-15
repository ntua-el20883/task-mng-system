package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        mainView.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
