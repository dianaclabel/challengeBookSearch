package com.aluracursos.challengeBookSearch.principal;

import com.aluracursos.challengeBookSearch.model.DatosLibro;
import com.aluracursos.challengeBookSearch.model.Libro;
import com.aluracursos.challengeBookSearch.services.ConsumoApi;
import com.aluracursos.challengeBookSearch.services.TransformarDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner keyboard = new Scanner(System.in) ;
    ConsumoApi api = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books?search=";

    TransformarDatos conversorDeJson = new TransformarDatos();


    public void interfaz(){
        System.out.println("Escribe el nombre del libro");
        var libro = keyboard.nextLine();
        System.out.println(URL_BASE+libro.replace(" ", "%20"));
        String json = api.obtenerDatosApi(URL_BASE+libro.replace(" ", "%20"));

        var infoLibro = conversorDeJson.conversor(json, DatosLibro.class);

        System.out.println("--------------------------------------");
        System.out.println("Todas las coincidencias : " + infoLibro);
        System.out.println("--------------------------------------");
        System.out.println("Te mostramos 5 coincidencias");

        infoLibro.libros().stream()
                .limit(5)
                .forEach(e -> System.out.println("Titulo: "+ e.titulo()+", Total de descarga: "+ e.totalDescargas()));


        //List<String> ArrayLibros = new ArrayList<>();









    }
}
