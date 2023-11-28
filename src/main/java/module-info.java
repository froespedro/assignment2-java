module com.example.cocktailproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.cocktailproject to javafx.fxml;
    exports com.example.cocktailproject;
}