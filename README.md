LiterAlura 📚
LiterAlura es una aplicación de consola desarrollada en Java con Spring Boot que funciona como un catálogo de libros interactivo. Permite a los usuarios buscar libros utilizando la API pública de Gutendex, guardar la información en una base de datos PostgreSQL y realizar diversas consultas sobre los libros y autores almacenados.

Este proyecto pone en práctica conceptos esenciales de desarrollo backend, consumo de APIs, y persistencia de datos con JPA.

✨ Funcionalidades Principales
La aplicación ofrece un menú interactivo en la consola con las siguientes opciones:

Buscar libro por título: Busca un libro en la API de Gutendex. Si se encuentra, guarda la información del libro y su autor en la base de datos local.

Listar libros registrados: Muestra una lista de todos los libros que han sido guardados en la base de datos.

Listar autores registrados: Muestra una lista de todos los autores almacenados, junto con sus años de nacimiento, fallecimiento y los títulos de sus libros.

Listar libros por idioma: Permite al usuario filtrar y ver todos los libros registrados en un idioma específico (ej. español, inglés, francés).

Listar autores vivos en un año determinado: Pide al usuario un año y muestra una lista de los autores que estaban vivos en ese período.

🛠️ Tecnologías Utilizadas
Lenguaje: Java 21

Framework: Spring Boot 3.5.3

Persistencia de Datos: Spring Data JPA

Base de Datos: PostgreSQL

Cliente HTTP: HttpClient nativo de Java

Manejo de JSON: Jackson Databind (ObjectMapper)

Gestión de Dependencias: Apache Maven

📋 Prerrequisitos
Antes de ejecutar el proyecto, asegúrate de tener instalado:

JDK (Java Development Kit): Versión 17 o superior.

Apache Maven: Versión 3.8 o superior.

PostgreSQL: Una instancia de base de datos activa y configurada.

Configuración de la Base de Datos
Deberás configurar la conexión a tu base de datos PostgreSQL en el archivo application.properties (o application.yml) de tu proyecto. Asegúrate de reemplazar tu_usuario, tu_contraseña, y tu_base_de_datos con tus credenciales:

spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🚀 Ejecución del Proyecto
Sigue estos pasos para ejecutar LiterAlura:

Clona el repositorio:

git clone https://github.com/tu-usuario/LiterAlura.git
cd LiterAlura

(Asegúrate de reemplazar https://github.com/tu-usuario/LiterAlura.git con la URL real de tu repositorio.)

Compila el proyecto con Maven:

mvn clean install

Ejecuta la aplicación:

mvn spring-boot:run

La aplicación se iniciará y te mostrará el menú interactivo en la consola.

🌐 API Externa
Este proyecto consume datos de Gutendex, una API web para libros de dominio público del Proyecto Gutenberg. Toda la información de los libros y autores se obtiene de este servicio.

👤 Autor
Realizado por Carlos Chávez F.
