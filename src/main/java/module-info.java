module iessanalberto.dam1.tictactoeclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens iessanalberto.dam1.tictactoeclient to javafx.fxml;
    exports iessanalberto.dam1.tictactoeclient;
}