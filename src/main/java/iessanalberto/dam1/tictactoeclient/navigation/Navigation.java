package iessanalberto.dam1.tictactoeclient.navigation;

import iessanalberto.dam1.tictactoeclient.network.Client;
import iessanalberto.dam1.tictactoeclient.screens.MainScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

    public class Navigation {
        private static Stage stage = new Stage();
        public static void navigate (String destination, Client client) {
            switch (destination) {
                case "MainScreen" -> {
                    MainScreen mainScreen = new MainScreen(client);
                    Scene mainScene = new Scene(mainScreen.getRoot(),320,240);
                    stage.setTitle("Tres en raya");
                    stage.setScene(mainScene);
                    stage.show();
                }
            }
        }
    }

