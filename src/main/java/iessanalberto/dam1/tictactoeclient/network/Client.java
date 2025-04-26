package iessanalberto.dam1.tictactoeclient.network;

import iessanalberto.dam1.tictactoeclient.screens.MainScreen;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;

public class Client {
    private String servidor = "18.213.122.78";
    private int puerto = 12345;
    private Socket socket;
    private MainScreen mainScreen;
    private char jugador;
    private boolean miTurno = false;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public Client(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;

    }

    public void setMiTurno(boolean miTurno) {
        this.miTurno = miTurno;
    }

    public boolean isMiTurno() {
        return miTurno;
    }

    public String getServidor() {
        return servidor;
    }

    public int getPuerto() {
        return puerto;
    }
    public char getJugador() {
        return jugador;
    }

    public void connect() {
        new Thread(() -> {
            try {
                socket = new Socket(servidor, puerto);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream(),true);
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    String finalLinea = linea;
                    Platform.runLater(() -> {
                        if (finalLinea.startsWith("Eres el jugador")) {
                            jugador = finalLinea.charAt(finalLinea.length() - 1);
                        } else {
                            mainScreen.getLblMensaje().setText(finalLinea);
                            procesarMensaje(finalLinea);
                        }
                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void procesarMensaje(String finalLinea) {
        if (finalLinea.startsWith("Tu turno.")) {
           miTurno = true;
        } else if (finalLinea.startsWith("Tablero actual")){
            mainScreen.actualizarTablero(finalLinea.substring("Tablero actual:".length()).replace("\\n", "\n").trim());
        } else if (finalLinea.startsWith("Ganador")){
            showAlert("Final del juego", "Ganador; " + finalLinea.split(":")[1]);
        } else if (finalLinea.startsWith("Empate")){
            showAlert("Final del juego","Empate");
        }

    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }



    public void send(String mensaje) {
        if (printWriter != null) {
            printWriter.println(mensaje);
        }
    }


    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }
}