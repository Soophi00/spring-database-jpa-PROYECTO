- BookAPI Project

This project is a simple RESTful API developed with **Spring Boot** and **Spring Data JPA**.  
It allows you to perform CRUD (Create, Read, Update, Delete) operations on a books database, stored in an **H2 in-memory database**.



- Features
- Add a new book (POST)
- Retrieve all books (GET)
- Search for books by title (GET)
- Delete a book by ID (DELETE)
- H2 Database Console for testing



- Technologies
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven



- How to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/Soophi00/spring-database-jpa-PROYECTO.git

 2.  Open the project in IntelliJ IDEA.

3.Make sure you have Maven dependencies downloaded.

4. Run the application from SpringDatabaseJpaApplication.java.

5. Access the API endpoints:

Get all books: GET http://localhost:8080/api/books

Add a book: POST http://localhost:8080/api/books

Search books by title: GET http://localhost:8080/api/books/search?title=Clean Code

Delete a book: DELETE http://localhost:8080/api/books/1

6. Access the H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:booksdb
Username: sa
Password: (leave it empty)

   Authors
Sophia Marcela Guzman Ayala  GA231577
Marcelo jose Almendarez Ramirez AR241049

