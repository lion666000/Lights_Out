module com.example.lights_out {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lights_out to javafx.fxml;
    exports com.example.lights_out;
}