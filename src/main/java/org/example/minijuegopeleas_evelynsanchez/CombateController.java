package org.example.minijuegopeleas_evelynsanchez;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

public class CombateController {
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
            boton1.setStyle("-fx-text-fill: #ffffff");
        });
        boton1.setOnMouseEntered(mouseEvent -> {
            boton1.setStyle("-fx-text-fill: #45264d");
        });

        boton2.setOnMouseExited(mouseEvent -> {
            boton2.setStyle("-fx-text-fill: #ffffff");
        });
        boton2.setOnMouseEntered(mouseEvent -> {
            boton2.setStyle("-fx-text-fill: #45264d");
        });

        boton3.setOnMouseExited(mouseEvent -> {
            boton3.setStyle("-fx-text-fill: #ffffff");
        });
        boton3.setOnMouseEntered(mouseEvent -> {
            boton3.setStyle("-fx-text-fill: #45264d");
        });
    }

    //--------------MÉTODOS GENERALES----------------------------
    //Botones, acumular poder(random stat) | pelear | regresar al void(lobby)
    @FXML
    private Label boton1, boton2, boton3;

    @FXML
    private void clickSalir(){
        Stage stage = (Stage) this.cabecera.getScene().getWindow();
        stage.close();
    }

    //Volver a la selección de personajes.
    @FXML
    private void regresar() {
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

            clickSalir();
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    //-------------LUCHA LUCHA-----------------------//
    //Aquí se pondrá el texto donde indicará con que stat se peleara
    @FXML
    private Label poder;
    private String [] stats = {"Fuerza", "Destreza", "Poder"};


    //Donde indicará con que se peleará aleatoriamente.
    @FXML
    private void acumularPoder() {
        int num = (int)Math.floor((Math.random()*3));
        poder.setText(stats[num]);
        boton1.setDisable(true);
        boton2.setDisable(false);
    }

    //Número aleatorio que se sumará junto al atributo. El de la izq = jugador 1 | der = jugador 2
    @FXML
    private Label acumular, acumular2;

    //Este método cuando se selecciona hará los números aleatorios y llamará al método auxiliar para calcular cual ganará.
    @FXML
    private void pelear() {
        int num = (int)Math.floor(Math.random()*100+1);
        acumular.setText(String.valueOf(num));
        int num2 = (int)Math.floor(Math.random()*100+1);
        acumular2.setText(String.valueOf(num2));

        int total1 = 0, total2 = 0; //Para almacenar los resultados finales del jugador 1 y 2.

        try {
            switch (poder.getText()) {
                case "Fuerza":
                    //Jugador 1:
                    total1 = num+Integer.parseInt(statFue.getText());
                    //Jugador 2:
                    total2 = num2+Integer.parseInt(statFue2.getText());
                    break;

                case "Destreza":
                    //Jugador 1:
                    total1 = num+Integer.parseInt(statDes.getText());
                    //Jugador 2:
                    total2 = num2+Integer.parseInt(statDes2.getText());
                    break;

                case "Poder":
                    //Jugador 1:
                    total1 = num+Integer.parseInt(statPod.getText());
                    //Jugador 2:
                    total2 = num2+Integer.parseInt(statPod2.getText());
                    break;

            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }

        calcularVida(total1, total2);
    }

    @FXML
    private Label victoria;
    //vidas
    private int vida=3, vida2=3;
    @FXML
    private ImageView cora1,cora2,cora3,cora21,cora22,cora23;

    //Este método es para separar el calculo de las vidas. Calcula cual de los dos gana y resta vida o aplica unos efectos cuando pierde uno de los dos.
    private void calcularVida(int total1, int total2){
        //Filtro de color para cuando pierda alguno de los jugadores,
        ColorAdjust gris = new ColorAdjust();
        gris.setSaturation(-1.0);

        if (total1>total2) {
            switch (vida2) {
                //corazon | vida total | botón del stat random | boton de pelear
                case 3: cora21.setVisible(false); vida2--; boton1.setDisable(false); boton2.setDisable(true); break;
                case 2: cora22.setVisible(false); vida2--; boton1.setDisable(false); boton2.setDisable(true); break;
                case 1: cora23.setVisible(false); vida2--; boton2.setDisable(true); jugador2.setEffect(gris);
                    AudioClip audio = new AudioClip(this.getClass().getResource("/sonido/perder.wav").toExternalForm());
                    audio.play();
                    victoria.setText("JUGADOR 1 GANA");
                    victoria.setVisible(true);
                    break;
            }
            sacudir(jugador2);
        } else if (total2>total1) {
            switch (vida) {
                //corazon | vida total | botón del stat random | boton de pelear
                case 3: cora3.setVisible(false); vida--; boton1.setDisable(false); boton2.setDisable(true); break;
                case 2: cora2.setVisible(false); vida--; boton1.setDisable(false); boton2.setDisable(true); break;
                case 1: cora1.setVisible(false); vida--; boton2.setDisable(true); jugador1.setEffect(gris);
                    AudioClip audio = new AudioClip(this.getClass().getResource("/sonido/perder.wav").toExternalForm());
                    audio.play();
                    victoria.setText("JUGADOR 2 GANA");
                    victoria.setVisible(true);
                    break;
            }
            sacudir(jugador1);
        } else {
            //Se sacuden en confusión, muy mal.
            sacudir(jugador1);
            sacudir(jugador2);
        }
    }

    //Sacude la imagen para efectos visuales y además reproduce un sonido de golpe.
    public void sacudir(ImageView imagen) {
        //Se crea el movimiento de la imagen.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(imagen.translateXProperty(),0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.translateXProperty(),-10)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(imagen.translateXProperty(),10)),
                new KeyFrame(Duration.seconds(0.6), new KeyValue(imagen.translateXProperty(),-10)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(imagen.translateXProperty(),10)),
                new KeyFrame(Duration.seconds(1), new KeyValue(imagen.translateXProperty(),0))
        );
        //Se reproduce el movimiento
        timeline.play();

        //Audio
        AudioClip audio = new AudioClip(this.getClass().getResource("/sonido/golpe.wav").toExternalForm());
        audio.play();
    }

    //---------------------PERSONAJES CREACION------------------------//
    //Imagenes de los personajes
    @FXML
    private ImageView jugador1, jugador2;
    //Stats de los personajes
    @FXML
    private Label statFue, statDes, statPod, statFue2, statDes2, statPod2;

    public void setPersonaje1(Image imagen, String fue, String des, String pod) {
        //JUGADOR 1
        jugador1.setImage(imagen);
        statFue.setText(fue);
        statDes.setText(des);
        statPod.setText(pod);
    }

    public void setPersonaje2(Image imagen, String fue, String des, String pod) {
        //JUGADOR 2
        jugador2.setImage(imagen);
        jugador2.setScaleX(-1); //Para que el personaje mire hacia la izquierda.
        statFue2.setText(fue);
        statDes2.setText(des);
        statPod2.setText(pod);
    }
}
//https://coderscratchpad.com/javafx-grayscale-image-filter/
//https://dev.java/learn/javafx-animations/
//https://stackoverflow.com/questions/23202272/how-to-play-sounds-with-javafx
//https://codingtechroom.com/question/play-sounds-javafx