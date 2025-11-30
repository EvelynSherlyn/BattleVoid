module org.example.minijuegopeleas_evelynsanchez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens org.example.minijuegopeleas_evelynsanchez to javafx.fxml;
    exports org.example.minijuegopeleas_evelynsanchez;
}