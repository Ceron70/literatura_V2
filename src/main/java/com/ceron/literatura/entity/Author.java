package com.ceron.literatura.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Entidad para la tabla de autores en la base de datos
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) // Asegura que los nombres de autor sean únicos
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    // Relación ManyToMany con Book. Un autor puede escribir muchos libros y un libro puede tener muchos autores.
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER) // MappedBy indica que la relación es gestionada por el campo 'authors' en la entidad Book
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Autor: " + name +
                (birthYear != null ? " (Nacimiento: " + birthYear + ")" : "") +
                (deathYear != null ? " (Fallecimiento: " + deathYear + ")" : "");
    }
}