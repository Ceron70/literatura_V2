package com.ceron.literatura;

import com.ceron.literatura.entity.Author; // Importar Author
import com.ceron.literatura.entity.Book;
import com.ceron.literatura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService; // Inyección del servicio de libros

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int option = -1;

		while (option != 0) {
			System.out.println("===Challenge Literatura / Carlos Chávez==");
			System.out.println("=========================================");
			System.out.println("Elija la opción a través de su número:");
			System.out.println("=========================================");
			System.out.println("1) Buscar libro por Titulo (escriba parte del titulo)");
			System.out.println("2) Listar libros registrados");
			System.out.println("3) Listar autores registrados");
			System.out.println("4) Listar autores vivos en un determinado año");
			System.out.println("5) Listar libros por idioma"); // Nueva opción
			System.out.println("0- Salir");
			System.out.println("=========================================");
			System.out.print("Opción: ");

			try {
				option = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Opción inválida. Por favor, ingrese un número.");
				continue; // Vuelve al inicio del bucle
			}

			switch (option) {
				case 1:
					System.out.print("Ingrese el nombre del libro que desea buscar: ");
					String title = scanner.nextLine();
					bookService.searchAndSaveBook(title); // Llama al servicio para buscar y guardar
					break;
				case 2:
					System.out.println("\n--- Libros Registrados ---");
					List<Book> books = bookService.listAllBooks(); // Llama al servicio para listar libros
					if (books.isEmpty()) {
						System.out.println("No hay libros registrados aún.");
					} else {
						books.forEach(System.out::println); // Imprime cada libro
					}
					System.out.println("--------------------------");
					break;
				case 3: // Nueva opción para listar autores
					System.out.println("\n--- Autores Registrados ---");
					List<Author> authors = bookService.listAllAuthors(); // Llama al servicio para listar autores
					if (authors.isEmpty()) {
						System.out.println("No hay autores registrados aún.");
					} else {
						authors.forEach(System.out::println); // Imprime cada autor
					}
					System.out.println("---------------------------");
					break;
				case 4: // Nueva opción para listar autores vivos en un año
					System.out.print("Ingrese el año para buscar autores vivos: ");
					try {
						Integer year = Integer.parseInt(scanner.nextLine());
						System.out.println("\n--- Autores Vivos en el Año " + year + " ---");
						List<Author> aliveAuthors = bookService.listAuthorsAliveInYear(year);
						if (aliveAuthors.isEmpty()) {
							System.out.println("No se encontraron autores vivos en el año " + year + ".");
						} else {
							aliveAuthors.forEach(System.out::println);
						}
						System.out.println("----------------------------------------");
					} catch (NumberFormatException e) {
						System.out.println("Año inválido. Por favor, ingrese un número entero.");
					}
					break;
				case 5: // Nueva opción para listar libros por idioma
					System.out.print("Ingrese el idioma para buscar libros (ej. es, en, fr): ");
					String language = scanner.nextLine();
					System.out.println("\n--- Libros en Idioma '" + language + "' ---");
					List<Book> booksByLanguage = bookService.listBooksByLanguage(language);
					if (booksByLanguage.isEmpty()) {
						System.out.println("No se encontraron libros registrados en el idioma '" + language + "'.");
					} else {
						booksByLanguage.forEach(System.out::println);
					}
					System.out.println("----------------------------------------");
					break;
					case 0:
					System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
					break;
				default:
					System.out.println("Opción no válida. Por favor, elija una opción del menú.");
					break;
			}
		}
		scanner.close(); // Cierra el scanner cuando la aplicación termina
	}


}
