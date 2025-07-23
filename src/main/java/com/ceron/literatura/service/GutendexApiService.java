package com.ceron.literatura.service;

import com.ceron.literatura.record.GutendexResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Servicio para interactuar con la API de Gutendex
@Service
public class GutendexApiService {

    private final String BASE_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate;

    public GutendexApiService() {
        this.restTemplate = new RestTemplate();
    }

    // Busca libros por título en la API de Gutendex
    public GutendexResponse searchBooksByTitle(String title) {
        String url = BASE_URL + "?search=" + title.replace(" ", "%20");
        try {
            // Realiza la petición GET y mapea la respuesta a GutendexResponse
            return restTemplate.getForObject(url, GutendexResponse.class);
        } catch (Exception e) {
            System.err.println("Error al buscar libros en Gutendex: " + e.getMessage());
            return null;
        }
    }
}