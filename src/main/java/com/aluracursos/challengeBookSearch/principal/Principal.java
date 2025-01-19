package com.aluracursos.challengeBookSearch.principal;

import com.aluracursos.challengeBookSearch.model.DatosLibro;
import com.aluracursos.challengeBookSearch.model.Libro;
import com.aluracursos.challengeBookSearch.services.ConsumoApi;
import com.aluracursos.challengeBookSearch.services.TransformarDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner keyboard = new Scanner(System.in) ;
    ConsumoApi api = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";

    TransformarDatos conversorDeJson = new TransformarDatos();


    public void interfaz(){
        //Busqueda de libro por nombre
        System.out.println("Escribe el nombre del libro");
        var libro = keyboard.nextLine();
        System.out.println(URL_BASE+libro.replace(" ", "%20"));
        String json = api.obtenerDatosApi(URL_BASE+"?search="+libro.replace(" ", "%20"));

        var infoLibros = conversorDeJson.conversor(json, DatosLibro.class);

        System.out.println("--------------------------------------");
        System.out.println("Todas las coincidencias : " + infoLibros);
        System.out.println("--------------------------------------");
        //System.out.println("Te mostramos 5 coincidencias");

        /*
        infoLibros.libros().stream()
                .limit(5)
                .forEach(e -> System.out.println("Titulo: "+ e.titulo()+", Total de descarga: "+ e.totalDescargas()));

         */

        //El opcional me permite usar metodos como : isPresent entre otros, ademas de guardar el dato cmo una variable

        Optional<Libro> libroBuscado = infoLibros.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(libro.toUpperCase())).findFirst();

        if (libroBuscado.isPresent()){
            System.out.println("Libro encontardo:");
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }





        //top de 10 libros mas descargados

        System.out.println("--------------------------------------");
        System.out.println("Top de los 10 libros mas descargados");

        String jsonTodo = api.obtenerDatosApi(URL_BASE);
        var todoLosLibros = conversorDeJson.conversor(jsonTodo, DatosLibro.class);

        todoLosLibros.libros().stream()
                .sorted(Comparator.comparing(Libro::totalDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        System.out.println("--------------------------------------");

        //TRABAJANDO CON ESTADISTICAS


        DoubleSummaryStatistics estadisticas = todoLosLibros.libros().stream()
                .filter(l -> l.totalDescargas() > 0)
                .peek(e -> System.out.println(e))
                .collect(Collectors.summarizingDouble(Libro::totalDescargas));

        System.out.println("--------------------------------------");

        System.out.println("cantidad maxima de descarga: " + estadisticas.getMax());
        System.out.println("canntidad minima de descarga: " + estadisticas.getMin());
        System.out.println("cantidad promedio de descarga: " + estadisticas.getAverage());
        System.out.println("Cantidad de registros evaluados en total: "+ estadisticas.getCount());










        //List<String> ArrayLibros = new ArrayList<>();









    }
}
