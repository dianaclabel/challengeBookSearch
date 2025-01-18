package com.aluracursos.challengeBookSearch.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obtenerDatosApi(String url) {


        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = null;

        try{
           response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e) {
            throw new RuntimeException(e);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() != 200) {
            return "Error al obtener datos de la API: Código de estado " + response.statusCode();
        }

        String json = response.body();
        return json;

    }

}
