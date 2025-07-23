package com.ceron.literatura.repository;

import com.ceron.literatura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

// Repositorio para la entidad Book
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Busca un libro por su título, ignorando mayúsculas y minúsculas
    Optional<Book> findByTitleIgnoreCase(String title);

    // Encuentra libros por idioma
    List<Book> findByLanguage(String language);
}