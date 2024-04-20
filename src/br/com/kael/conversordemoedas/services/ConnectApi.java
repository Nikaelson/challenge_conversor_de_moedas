package br.com.kael.conversordemoedas.services;

import br.com.kael.conversordemoedas.models.Conversion;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectApi {

    public Conversion getConversion(String[] currencySymbols, double amont){
        String apiKey = System.getenv("API_KEY");

        URI enderco = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + currencySymbols[0] + "/" + currencySymbols[1] + "/" + amont);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(enderco)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Conversion.class);
        }catch (Exception e){
            throw new RuntimeException("Problema na chamada da APi");
        }
    }
}
