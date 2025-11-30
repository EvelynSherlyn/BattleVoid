package org.example.minijuegopeleas_evelynsanchez;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 @author: Evelyn Sherlyn
 Una pantalla inicial simple para salir y entrar al juego.
 */
public class HelloController {
//----------------------------CABECERA-----------------------------
//Esta sección es para modificar la cabecera para que sea movible.

    //Para hacer los puntos x e y que se alterarán de la cabecera.
    private double xoffset = 0;
    private double yOffset = 0;

    @FXML
    private ToolBar cabecera;

    @FXML
    public void initialize() {
        cabecera.setOnMousePressed(mouseEvent -> {
    //Se actualizan los puntos x e y de la cabecera.
            xoffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();

    //Cierra el cursor de la mano cuando se está pulsando en la cabecera.
            cabecera.setCursor(Cursor.CLOSED_HAND); //Si haces un Cursor te ofrece los diferentes tipos de cursores del Scene builder para ponerlo manualmente.
        });

        cabecera.setOnMouseDragged(mouseEvent -> {
    //Se inicializa un tipo Stage, donde será el que tenemos, ya que adquerimos este a través de la instancia asociada a "cabecera".
            Stage stage = (Stage) cabecera.getScene().getWindow();

    //Una vez llamado a stage, podemos manipular sus coordenas x e y en función a la resta de donde lo arrastremos con el valor actual x e y de la cabecera pulsada.
            stage.setX(mouseEvent.getScreenX()-xoffset);
            stage.setY(mouseEvent.getScreenY()-yOffset);
        });

    //Por motivos esteticos del cursor.
        cabecera.setOnMouseReleased(mouseEvent -> {
    //Abre la mano del cursor cuando se suelta la cabecera.
            cabecera.setCursor(Cursor.OPEN_HAND);
        });

        //""hover"" para los botones
        boton1.setOnMouseExited(mouseEvent -> {
            boton1.setStyle("-fx-background-color: #f0edf5; -fx-border-color: #161828; -fx-border-width: 4;");
        });
        boton1.setOnMouseEntered(mouseEvent -> {
            boton1.setStyle("-fx-background-color: #696c80; -fx-border-color: #161828; -fx-border-width: 4;");
        });

        boton2.setOnMouseExited(mouseEvent -> {
            boton2.setStyle("-fx-background-color: #f0edf5; -fx-border-color: #161828; -fx-border-width: 4;");
        });
        boton2.setOnMouseEntered(mouseEvent -> {
            boton2.setStyle("-fx-background-color: #696c80; -fx-border-color: #161828; -fx-border-width: 4;");
        });
    }

    //---------------------------------------------------
    @FXML
    private Button boton1,boton2;

    @FXML
    private void clickSalir(){
        System.exit(0);
    }

    //Entra al menú de selección de personajes.
    @FXML
    private void clickPersonajes() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("selectorPersonajes.fxml"));
        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage= new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setTitle("Selector de personaje");

            stage.setScene(scene);

            stage.show();

            Stage stageMain = (Stage) this.boton1.getScene().getWindow();
            stageMain.close();
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}