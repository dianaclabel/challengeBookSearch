package com.aluracursos.challengeBookSearch.services;

import com.aluracursos.challengeBookSearch.model.DatosLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformarDatos implements ITransformarDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T conversor(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
