package com.ceron.literatura.service;

import com.ceron.literatura.entity.Author;
import com.ceron.literatura.entity.Book;
import com.ceron.literatura.record.AuthorData;
import com.ceron.literatura.record.BookData;
import com.ceron.literatura.record.GutendexResponse;
import com.ceron.literatura.repository.AuthorRepository;
import com.ceron.literatura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// Servicio principal para la lógica de negocio de libros y autores
@Service
public class BookService {

    @Autowired
    private GutendexApiService gutendexApiService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    // Busca un libro por título en la API y lo guarda en la base de datos si no existe
    @Transactional
    public void searchAndSaveBook(String title) {
        // Primero, verifica si el libro ya está en la base de datos
        Optional<Book> existingBook = bookRepository.findByTitleIgnoreCase(title);
        if (existingBook.isPresent()) {
            System.out.println("El libro '" + title + "' ya está registrado en la base de datos.");
            System.out.println(existingBook.get());
            return;
        }

        // Si no está, búscalo en la API de Gutendex
        GutendexResponse response = gutendexApiService.searchBooksByTitle(title);

        if (response != null && !response.results().isEmpty()) {
            // Toma el primer resultado de la búsqueda
            BookData bookData = response.results().getFirst();

            // Crea una nueva entidad Book
            Book book = new Book(bookData.title(),
                    bookData.languages().isEmpty() ? "N/A" : bookData.languages().getFirst(),
                    bookData.downloadCount());

            // Procesa los autores
            for (AuthorData authorData : bookData.authors()) {
                // Busca si el autor ya existe en la base de datos
                Optional<Author> existingAuthor = authorRepository.findByNameIgnoreCase(authorData.name());
                Author author;
                if (existingAuthor.isPresent()) {
                    author = existingAuthor.get();
                } else {
                    // Si el autor no existe, crea uno nuevo y guárdalo
                    author = new Author(authorData.name(), authorData.birthYear(), authorData.deathYear());
                    authorRepository.save(author); // Guarda el autor antes de asociarlo al libro
                }
                book.addAuthor(author); // Asocia el autor al libro
            }

            // Guarda el libro (esto también persistirá los autores si son nuevos debido a CascadeType.ALL)
            bookRepository.save(book);
            System.out.println("Libro guardado exitosamente:");
            System.out.println(book);
        } else {
            System.out.println("No se encontraron resultados para el libro: " + title);
        }
    }

    // Lista todos los libros registrados en la base de datos
    public List<Book> listAllBooks() {
        return bookRepository.findAll();

    }
    // Nuevo método: Lista todos los autores registrados en la base de datos
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    // Nuevo método: Lista autores vivos en un año determinado
    public List<Author> listAuthorsAliveInYear(Integer year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }

    // Nuevo método: Lista libros por idioma
    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

}