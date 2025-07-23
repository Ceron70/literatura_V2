package com.ceron.literatura.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Entidad para la tabla de libros en la base de datos
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) // Asegura que los títulos de libros sean únicos
    private String title;
    private String language;
    private Integer downloadCount;

    // Relación ManyToMany con Author. Un libro puede tener muchos autores.
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // CascadeType.ALL para persistir autores nuevos
    @JoinTable(
            name = "book_author", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "book_id"), // Columna para Book
            inverseJoinColumns = @JoinColumn(name = "author_id") // Columna para Author
    )
    private List<Author> authors = new ArrayList<>();

    public Book() {}

    public Book(String title, String language, Integer downloadCount) {
        this.title = title;
        this.language = language;
        this.downloadCount = downloadCount;
    }

    // Método para agregar un autor a la lista de autores del libro
    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        // Formatea la lista de autores para mostrar solo los nombres
        String authorNames = authors.stream()
                .map(Author::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconocido");

        return "---------- LIBRO ----------\n" +
                "Título: " + title + "\n" +
                "Autor: " + authorNames + "\n" +
                "Idioma: " + language + "\n" +
                "Número de descargas: " + downloadCount + "\n" +
                "---------------------------\n";
    }
}