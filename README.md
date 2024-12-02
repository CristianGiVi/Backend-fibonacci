# FibonacciSerie

Fibonacci es una API REST desarrollada con Spring Boot para generar y almacenar secuencias de Fibonacci basadas en la hora del sistema o en un tiempo proporcionado por el usuario.
La secuencia se genera de la siguiente manera: La semilla X son los segundos, la semilla Y son los minutos y el largo de la serie es la hora



## Funcionalidades Actuales

- Obtención de una lista de secuencias de Fibonacci previamente generadas y almacenadas en la base de datos.
- Generación de una secuencia de Fibonacci utilizando la hora actual del sistema.
- Generación de una secuencia de Fibonacci basada en un tiempo específico proporcionado en la solicitud.

## Tecnologías Utilizadas

- Spring Boot: Framework Java para crear aplicaciones web y APIs RESTful.
- Spring Data JPA: Para la interacción con la base de datos MySQL.
- MySQL: Base de datos relacional para almacenar las secuencias de Fibonacci generadas.
- Swagger/OpenAPI: Para la documentación y pruebas interactivas de la API.
- Java 21: Lenguaje de programación utilizado para el backend.

## Instalación y Uso

Requisitos previos:

- JDK 21 instalado en tu sistema.
- MySQL configurado localmente con una base de datos llamada fibonacci.

1. Clona el repositorio en tu máquina local

2. Agrega al archivo `application.properties` con los siguientes datos de conexión a la base de datos:

```
spring.datasource.url=jdbc:mysql://localhost:3306/fibonacci
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

3. Ejecuta la aplicacion

4. La aplicación estará disponible en http://localhost:8080.

5. Link swagger: http://localhost:8080/swagger-ui/index.html#/

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (git checkout -b nueva-funcionalidad).
3. Realiza tus cambios y haz commits (git commit -am 'Agrega nueva funcionalidad').
4. Haz push a la rama (`git push origin nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

## Autores

- Cristian Giraldo Villegas [CristianGiVi](https://github.com/CristianGiVi)

