package com.aluracursos.challengeBookSearch.principal;

import com.aluracursos.challengeBookSearch.model.DatosLibro;
import com.aluracursos.challengeBookSearch.model.Libro;
import com.aluracursos.challengeBookSearch.services.ConsumoApi;
import com.aluracursos.challengeBookSearch.services.TransformarDatos;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner keyboard = new Scanner(System.in) ;
    ConsumoApi api = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";

    TransformarDatos conversorDeJson = new TransformarDatos();


    public void interfaz(){
        System.out.println("Escribe el nombre del libro");
        var libro = keyboard.nextLine();
        System.out.println(URL_BASE+libro.replace(" ", "%20"));
        String json = api.obtenerDatosApi(URL_BASE+"?search="+libro.replace(" ", "%20"));

        var infoLibros = conversorDeJson.conversor(json, DatosLibro.class);

        System.out.println("--------------------------------------");
        System.out.println("Todas las coincidencias : " + infoLibros);
        System.out.println("--------------------------------------");
        System.out.println("Te mostramos 5 coincidencias");

        infoLibros.libros().stream()
                .limit(5)
                .forEach(e -> System.out.println("Titulo: "+ e.titulo()+", Total de descarga: "+ e.totalDescargas()));


        //top de 10 libros mas descargados

        System.out.println("--------------------------------------");
        System.out.println("Top de los 10 libros mas descargados");

        String jsonTodo = api.obtenerDatosApi(URL_BASE);
        var todoLosLibros = conversorDeJson.conversor(jsonTodo, DatosLibro.class);

        DoubleSummaryStatistics estadisticas = todoLosLibros.libros().stream()
                .limit(10)
                .peek(e -> System.out.println(e))
                .collect(Collectors.summarizingDouble(Libro::totalDescargas));

        System.out.println("--------------------------------------");

        System.out.println("Libro con Mayor descarga: " + estadisticas.getMax());
        System.out.println("Libro con Menor descarga: " + estadisticas.getMin());
        System.out.println("Registros en total: "+ estadisticas.getCount());








        //List<String> ArrayLibros = new ArrayList<>();









    }
}
