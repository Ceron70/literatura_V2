package com.ceron.literatura.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Record para mapear la respuesta completa de la API de Gutendex
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades no mapeadas
public record GutendexResponse(
        @JsonAlias("results") List<BookData> results
) {
}