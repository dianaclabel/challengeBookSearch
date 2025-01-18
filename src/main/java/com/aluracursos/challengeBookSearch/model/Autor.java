package com.aluracursos.challengeBookSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true)

public record Autor(

       @JsonAlias("name") String nombre,
       @JsonAlias("birth_year") int fechaNacimiento,
       @JsonAlias("death_year") int fechaFallecimiento
) {
}
