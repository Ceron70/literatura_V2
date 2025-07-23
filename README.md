# LiterAlura 📚

LiterAlura es una aplicación de consola desarrollada en **Java** y **Spring Boot** que funciona como un catálogo de libros interactivo. Permite a los usuarios buscar libros utilizando la API pública de **Gutendex**, guardar la información en una base de datos **PostgreSQL** y realizar diversas consultas sobre los libros y autores almacenados.

Este proyecto pone en práctica conceptos de desarrollo backend, consumo de APIs y persistencia de datos con JPA.

---

## ✨ Funcionalidades Principales

La aplicación ofrece un menú interactivo en la consola con las siguientes opciones:

1.  **Buscar libro por título**: Busca un libro en la API de Gutendex. Si se encuentra, guarda la información del libro y su autor en la base de datos local.
2.  **Listar libros registrados**: Muestra una lista de todos los libros que han sido guardados en la base de datos.
3.  **Listar autores registrados**: Muestra una lista de todos los autores almacenados, junto con sus años de nacimiento, fallecimiento y los títulos de sus libros.
4.  **Listar libros por idioma**: Permite al usuario filtrar y ver todos los libros registrados en un idioma específico (ej. español, inglés, francés).
5.  **Listar autores vivos en un año determinado**: Pide al usuario un año y muestra una lista de los autores que estaban vivos en ese período.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje**: Java 21
* **Framework**: Spring Boot 3.5.3
* **Persistencia de Datos**: Spring Data JPA
* **Base de Datos**: PostgreSQL
* **Cliente HTTP**: HttpClient nativo de Java
* **Manejo de JSON**: Jackson Databind (ObjectMapper)
* **Gestión de Dependencias**: Apache Maven

---

## 📋 Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

* **JDK (Java Development Kit)**: Versión 17 o superior.
* **Apache Maven**: Versión 3.8 o superior.
* **PostgreSQL**: Una instancia de base de datos activa.

---

## 🌐 API Externa

Este proyecto consume datos de [Gutendex](https://gutendex.com/), una API web para libros de dominio público del Proyecto Gutenberg. Toda la información de los libros y autores se obtiene de este servicio.

---

Realizado por **Carlos Chávez F.**
