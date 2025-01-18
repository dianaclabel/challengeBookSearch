package com.aluracursos.challengeBookSearch.services;

public interface ITransformarDatos {
    <T> T conversor ( String json, Class<T> clase);
}
