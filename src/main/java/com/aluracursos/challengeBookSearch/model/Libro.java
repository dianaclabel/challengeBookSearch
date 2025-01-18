package com.aluracursos.challengeBookSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true)

public record Libro(
        @JsonAlias("title")  String titulo,
        @JsonAlias("authors") List<Autor> autor,
        @JsonAlias("download_count") int totalDescargas
) {
}
