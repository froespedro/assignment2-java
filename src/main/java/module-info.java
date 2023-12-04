module com.example.cocktailproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    // Add these lines to open your package to javafx.graphics
    opens com.example.cocktailproject to javafx.fxml, javafx.graphics;
    exports com.example.cocktailproject;
}
