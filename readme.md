# PDF-Manager

PDF-Manager is a SpringBoot based web application, used to store and serve pdf files. Files are stored in a SQLite database, automatically generated

### Prerequisites

- Java 8+
- Maven 3+

### Installing

To install and run:
```
mvn clean install
cd target
java -jar ./pdf_manager-0.0.1-SNAPSHOT.jar
```

Once started, user interface is available at [http://localhost:8080](http://localhost:8080)


## Built With

* [SQLite](https://www.sqlite.org/) - Database
* [Spring Boot](https://spring.io/projects/spring-boot) - Backend functionalities
* [Angular](https://angular.io/) - User interface