package com.example.cocktailproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    private APICocktails apiCocktails = new APICocktails();

    @FXML
    protected void onSearchButtonClick() {
        new Thread(() -> {
            try {
                String cocktailName = searchField.getText();
                String jsonResponse = apiCocktails.getCocktailsByName(cocktailName);


                List<String> cocktailNames = parseCocktailNames(jsonResponse);


                Platform.runLater(() -> {
                    resultsList.getItems().setAll(cocktailNames);
                });
            } catch (IOException e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    resultsList.getItems().clear();
                    resultsList.getItems().add("Failed to fetch data: " + e.getMessage());
                });
            }
        }).start();
    }

    private List<String> parseCocktailNames(String jsonData) {
        List<String> names = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
        JsonArray drinks = jsonObject.getAsJsonArray("drinks");

        if (drinks != null) {
            for (int i = 0; i < drinks.size(); i++) {
                JsonObject drink = drinks.get(i).getAsJsonObject();
                String name = drink.get("strDrink").getAsString();
                names.add(name);
            }
        }

        return names;
    }

    @FXML
    public void initialize() {
    }
}
