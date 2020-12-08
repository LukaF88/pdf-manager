# PDF-Manager

PDF-Manager is a SpringBoot based web application, used to store and serve pdf files. Files are stored in a SQLite database, automatically generated

### Prerequisites

- openjdk 11.0.8
- Maven 3+

### Installing

To install and run:
```
git clone https://github.com/LukaF88/pdf-manager.git
cd pdf-manager
mvn clean install
cd target
java -jar ./pdf_manager-0.0.1-SNAPSHOT.jar
```

Once started, user interface is available at [http://localhost:8080](http://localhost:8080). Credentials for initial login are "user" and "pass". Swagger available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Built With

* [SQLite](https://www.sqlite.org/) - Database
* [Spring Boot](https://spring.io/projects/spring-boot) - Backend functionalities
* [Angular](https://angular.io/) - User interface
