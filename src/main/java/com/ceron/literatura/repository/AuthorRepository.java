package com.ceron.literatura.repository;

import com.ceron.literatura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repositorio para la entidad Author
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Busca un autor por su nombre, ignorando mayúsculas y minúsculas
    Optional<Author> findByNameIgnoreCase(String name);

    // Nuevo método: Encuentra autores que estaban vivos en un año determinado
    // Un autor está vivo en un año 'y' si:
    // (año_nacimiento <= y) AND (año_fallecimiento IS NULL OR año_fallecimiento >= y)
    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAuthorsAliveInYear(Integer year);
}