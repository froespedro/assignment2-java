package com.example.cocktailproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APICocktails {

    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";

    public String getCocktailsByName(String name) throws IOException {
        String query = BASE_URL + "search.php?s=" + name;
        URL url = new URL(query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();


        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        Scanner sc = new Scanner(url.openStream());
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
        }
        sc.close();
        return sb.toString();
    }
}
