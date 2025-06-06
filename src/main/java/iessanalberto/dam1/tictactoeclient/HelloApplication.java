package iessanalberto.dam1.tictactoeclient;

import iessanalberto.dam1.tictactoeclient.navigation.Navigation;
import iessanalberto.dam1.tictactoeclient.network.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigation.navigate("MainScreen",new Client("18.213.122.78",12345));
    }

    public static void main(String[] args) {
        launch();
    }
}