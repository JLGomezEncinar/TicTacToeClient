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

    public MainScreen() {
        client = new Client (client.getServidor(),client.getPuerto());
        client.connect();
        for (int fila = 0; fila < 3; fila++){
            for (int columna = 0; columna < 3; columna++){
                final int finalFila = fila;
                final int finalColumna = columna;
                Button casilla = new Button();
                casilla.setOnAction(event -> {
                    if (client.isMiTurno() && casilla.getText().equals(" ")){
                        casilla.setText(String.valueOf(client.getJugador()));
                        client.setMiTurno(true);

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
        String [] lineas = celdas.split("\n");
        for (int i = 0, r = 0; i < lineas.length; i += 2, r++) {
            String[] symbols = lineas[i].trim().split("\\|");
            for (int c = 0; c < 3; c++) {
                casillas[r][c].setText(symbols[c].trim());
            }
        }
    }
}
