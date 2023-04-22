# Read Me First
The following was discovered as part of building this project:

* The original package name 'edu.dharbor.bootcamp.rick-and-morty' is invalid and this project uses 'edu.dharbor.bootcamp.rickandmorty' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.10/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.10/maven-plugin/reference/html/#build-image)

**API**: [Rick y Morty API](https://rickandmortyapi.com/)

**Ide**:
- Verificar la configuracion de JAVA 11
- Verficiar la configuracion de maven
- [Instalar LOMBOK plugin](https://projectlombok.org/setup/intellij)

Dependecias:
- [Lombok](https://projectlombok.org/setup/maven)
- [Mapstructs](https://mapstruct.org/documentation/installation/)
- [Mapstructs and lombok](https://bootify.io/spring-data/mapstruct-with-maven-and-lombok.html)
- [Maven compiler plugin](https://maven.apache.org/plugins/maven-compiler-plugin/examples/compile-using-different-jdk.html)

Tools:
- [Crear proyecto en sprint boot](https://start.spring.io/)
- [recursos a ignorar en GIT](https://www.toptal.com/developers/gitignore)

Client REST
- [POSTMAN](https://www.postman.com/downloads/)
- (VS Code - Thunder client)(https://www.thunderclient.com/)
- (VS Code - rapid api)[https://rapidapi.com/guides/categories/rapidapi-client-vscode]

Motores de base de datos:
MySQL
* [Instalacion](https://www.mysql.com/downloads/)
* Docker:
- ```docker pull mysql:latest```
- ```docker run -d -p 3306:3306 --name mysql-bootcamp -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=rick_and_morty_db -e MYSQL_USER=student -e MYSQL_PASSWORD=student mysql:latest```

    Definicion:
```
      CREATE TABLE `character_` (
        `id_` int NOT NULL AUTO_INCREMENT,
        `registration_id_` int NOT NULL,
        `name_` varchar(100),
        `status_` varchar(25),
        `species_` varchar(100),
        `type_` varchar(100),
        `gender_` varchar(25),
        `image_` varchar(250),
        `url_` varchar(250),
        `created_` datetime,
        PRIMARY KEY (`id_`),
        UNIQUE KEY `id_UNIQUE` (`id_`)
      )
```

Mongo DB
* [Instalacion](https://www.mongodb.com/docs/manual/installation/)
* Docker:
- ```docker pull mongo:latest```
- ```docker run -d -p 27017:27017 --name mongo-bootcamp mongo:latest```