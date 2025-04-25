package iessanalberto.dam1.tictactoeclient.screens;

import iessanalberto.dam1.tictactoeclient.network.Client;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainScreen {
    private VBox root = new VBox();
    private GridPane gridPane = new GridPane();


    private Label lblMensaje = new Label();
    private Client client;

    private Button[][] casillas = new Button [3][3];

    public MainScreen(Client client) {
        this.client = client;
        client.setMainScreen(this);
        client.connect();
        for (int fila = 0; fila < 3; fila++){
            for (int columna = 0; columna < 3; columna++){
                final int finalFila = fila;
                final int finalColumna = columna;
                Button casilla = new Button();
                casilla.setOnAction(event -> {
                    if (client.isMiTurno() && casilla.getText().isEmpty()){
                        casilla.setText(String.valueOf(client.getJugador()));
                        client.send(finalFila + "," + finalColumna);
                        client.setMiTurno(false);

                    }
                });
                casillas[finalFila][finalColumna] = casilla;
                gridPane.add(casilla,columna,fila);
            }

        }

        root.getChildren().addAll(gridPane,lblMensaje);

    }




    public Label getLblMensaje() {
        return lblMensaje;
    }

    public VBox getRoot() {
        return root;
    }
    public void actualizarTablero(String celdas) {
        String[] lineas = celdas.split("\n");
        int r = 0;
        for (String linea : lineas) {
            if (!linea.contains("-")) {
                String[] symbols = linea.trim().split("\\|");
                for (int c = 0; c < 3; c++) {
                    casillas[r][c].setText(symbols[c].trim());
                }
                r++;
            }
        }
    }
}
