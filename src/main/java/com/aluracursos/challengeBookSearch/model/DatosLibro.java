package com.aluracursos.challengeBookSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties( ignoreUnknown = true)
public record DatosLibro(

      @JsonAlias("count") int totalDeLibros,
      @JsonAlias("results") List<Libro> libros


) {
}
