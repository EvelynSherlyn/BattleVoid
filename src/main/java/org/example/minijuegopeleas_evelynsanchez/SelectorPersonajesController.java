package org.example.minijuegopeleas_evelynsanchez;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SelectorPersonajesController {
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

        //hover
        boton1.setOnMouseExited(mouseEvent -> {
            boton1.setStyle("-fx-background-color: #f0edf5; -fx-border-color: #161828; -fx-border-width: 4;");
        });
        boton1.setOnMouseEntered(mouseEvent -> {
            boton1.setStyle("-fx-background-color: #696c80; -fx-border-color: #161828; -fx-border-width: 4;");
        });
    }

    //---------------------------------------------------
    @FXML
    private Button boton1;

    @FXML
    private void clickSalir(){
       Stage stage = (Stage) this.cabecera.getScene().getWindow();
       stage.close();
    }

    //Dirigue a la pantalla de combate.
    @FXML
    private void clickCombate() {
        if (jugador1Activo && jugador2Activo) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("combate.fxml"));
            try {
                Parent root = loader.load();

                CombateController controller = loader.getController();
                controller.setPersonaje1(imagenFinal1, statFue, statDes, statPod); //Se establece el personaje del jugador 1
                controller.setPersonaje2(imagenFinal2, statFue2, statDes2, statPod2); //Se establece el personaje del jugador 2

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                stage.setTitle("Combate");

                stage.setScene(scene);

                stage.show();

                clickSalir();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    //--------------JUGADORES--------------------
    //Las imagenes que se verán de los personajes elegidos, izquierda del jugador uno, derecha del jugador dos.
    @FXML
    private ImageView jugador1, jugador2;
    //Imagenes que se verán reflejados en el combate, segun lo que eligieron los jugadores.
    private Image imagenFinal1, imagenFinal2;


    //Controles booleano para saber si ya tienen elegido los jugadores un personaje o no. false == no elegi nada.
    private boolean jugador1Activo = false;
    private boolean jugador2Activo = false;

    //Para reflejar en pantalla el nombre del personaje y los stats, meramente informativo y estetico para el jugador.
    @FXML
    private Label nombre1, nombre2;
    @FXML
    private Label stats1, stats2;

    //Estos serán los stats que me llavaré a la siguiente pantalla.
    private String statFue, statDes, statPod, statFue2, statDes2, statPod2;

    //Se usa para cuando el jugador hace click en la imagen del personaje o selecciona el mismo personaje en la seleccion para deseleccionarlo.
    //Pone todo a NULL cuando el jugador 1 deselecciona el personaje
    @FXML
    private void clickQuitarPersonaje() {
        jugador1.setImage(null);
        nombre1.setText(null);
        stats1.setText(null);

        statFue = null;
        statDes = null;
        statPod = null;

        imagenFinal1 = null;

        jugador1Activo = false; //El jugador 1 no tiene personaje elegido
    }
    //Pone todo a NULL cuando el jugador 2 deselecciona el personaje
    @FXML
    private void clickQuitarPersonaje2() {
        jugador2.setImage(null);
        nombre2.setText(null);
        stats2.setText(null);

        statFue2 = null;
        statDes2 = null;
        statPod2 = null;

        imagenFinal2=null;

        jugador2Activo = false; //El jugador 2 no tiene personae elegido
    }

    //Método auxiliar para poner las imagenes
    // Url imagen visual para el jugador | url imagen del combate | nombre personaje | stats del personaje (para llevar a la siguiente pantalla).
    private void cambiarPersonaje(String url, String urlFinal, String nombre, String stats, String fue, String des, String pod) {
        Image imagen = new Image(getClass().getResource(url).toExternalForm());
        if (!jugador1Activo) {
            jugador1.setImage(imagen);
            movimiento(jugador1);

            nombre1.setText(nombre);
            stats1.setText(stats);

            statFue = fue;
            statDes = des;
            statPod = pod;

            imagenFinal1 = new Image(getClass().getResource(urlFinal).toExternalForm());

            jugador1Activo = true; //EL jugador 1 ha seleccionado personaje
        } else if (!jugador2Activo) {
            jugador2.setImage(imagen);
            movimiento(jugador2);

            nombre2.setText(nombre);
            stats2.setText(stats);

            statFue2 = fue;
            statDes2 = des;
            statPod2 = pod;

            imagenFinal2 = new Image(getClass().getResource(urlFinal).toExternalForm());

            jugador2Activo = true; //El jugador 2 ha seleccionado personaje
        } else {
            if (jugador1.getImage().getUrl().equals(imagen.getUrl())) {
                clickQuitarPersonaje();
            } else {
                clickQuitarPersonaje2();
            }
        }
    }

    //Genera un movimiento a la imagen, donde desplaza de derecha a izquierda.
    private void movimiento(ImageView imagen) {
        //Se crea el movimiento de la imagen
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(imagen.translateXProperty(),30)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(imagen.translateXProperty(),0))
        );
        //Se reproduce el movimiento
        timeline.play();

        //De transparente a visible
        FadeTransition trans = new FadeTransition(Duration.seconds(0.1), imagen);
        trans.setFromValue(0);
        trans.setToValue(1.0);
        trans.setInterpolator(Interpolator.LINEAR);

        trans.play();
    }

    //-----------------PERSONAJES INFO------------------//
    //Imagen chiquita | Imagen de combate | Nombre del personaje | Stats que verá el jugador | stats que serán llevadas a la otra pantalla.
    @FXML
    private void paco(){
        cambiarPersonaje("/miniPersonajes/PacoChiquito.png", "/personajes/Paco.png", "Paco", "Fuerza:65     Destreza:30     Poder:80", "65","30","80");
    }
    @FXML
    private void kuka(){
        cambiarPersonaje("/miniPersonajes/KukaChiquita.png", "/personajes/Kuka.png", "Kuka", "Fuerza:55     Destreza:55     Poder:55","55","55","55");
    }
    @FXML void dione() {
        cambiarPersonaje("/miniPersonajes/DioneChiquita.png", "/personajes/Dione.png", "Dione", "Fuerza:50     Destreza:90     Poder:60","50","90","60");
    }
}
