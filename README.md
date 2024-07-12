# Literalura

> **Disclaimer:** Con fines de aprendizaje, toda la implementación ha sido realizada en inglés.

Este repositorio contiene la solución al reto `LiterAlura` del programa Oracle ONE la cual consiste en la consulta de libros a través de la API de [Gutendex](https://gutendex.com/) para su posterior almmacenamiento en una base de datos PostgreSQL.

## Pre Requisitos

El proyecto fue desarrollado con JAVA 17.

Así mismo, el proyecto asume que tienes un servidor de base de datos disponible, en este caso se PostgreSQL, pero con las modificaciones necesarias se puede adaptar a cualquier otro proveedor.

Para el desarrollo y ejecución del proyecto, es necesario crear un archivo llamado `secrets-config.properties` dentro de la ruta /src/main/resources/ (al mismo nivel que el archivo `application.yml`) con los siguientes valores:

```
DATABASE_HOST=<IP del servidor>
DATABASE_PORT=<Puerto del servidor>
DATABASE_NAME=<Nombre de la base de datos>
DATABASE_USERNAME=<Usuario de la base de datos>
DATABASE_PASSWORD=<Contraseña del usuario>
```

## Operaciones

Esta aplicación en consola te permite:
- Consultar un libro directamente desde la API de Gutendex, si este se encuentra en la API, se guardará en la base de datos.
- Listar todos los libros almacenados en la base de datos.
- Listar todos los autore almacenados en la base de datos.
- Listar todos los autores vivos a un determinado año.
- Listar los libros por lenguaje.

El autor es validado antes de ser guardado, por lo que si el autor ya existe en la base de datos, se usará la información ya guardada en lugar de crear un nuevo registro para el autor.

Del mismo modo, el título del libro es validado en conjunto con el nombre del autor, si ya existe un título y autor con los mismo valores que el nuevo libro, no se guardará el resultado y se mostrará un mensaje diciendo que el libro ya existe.