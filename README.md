# Online-Book-Store

# CRUD-Spring-Boot-JPA-MySQL

# CRUD Example of Spring-Boot-REST-JPA-MySQL (BookStore)

### 1. You can clone it from github by running following command


  $ git clone https://github.com/Tamil0706/Online-Book-Store.git

### 2. Import project into eclipse

  File -> Import -> Maven -> Existing Maven Projects -> Browse Project from cloned location

### 3. Right click on project in eclipse and then Maven -> Update Projects 


### 6. Update database credential and other configuration into application.properties available in src/main/java/resources


server.port=8088

# Database connection properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore?useSSL=false
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# App Properties
app.jwt.secret= ======================SecretKey=BookStore======================================================
app.jwt.expiration=3600

management.endpoints.web.exposure.include=loggers
management.endpoint.loggers.enabled=true
management.security.enabled=false

spring.http.converters.preferred-json-mapper=gson




### 7. Right click on Application.java file and run as Java Application

## Once Sprint Boot Application will be started successfully then we 
can call following Endpoints by using POSTMAN

### 8. To get list of books call following endpoint with GET Request

  http://localhost:8080/api/books

### 9.To Create New Book use following url with POST Request

  http://localhost:8080/api/books

### set content type as in Body as `raw/json`
### set request body as raw with JSON payload

  {
    "isbn": 145121891,
    "bookName": "Educated",
    "price": 50,
    "categories": "Memoir",
    "review": "A heartwrenching and inspiring memoir about a woman's journey from an isolated and abusive upbringing to earning a PhD from Cambridge University.",
    "author": {
        "aurthorid": 12,
        "authorName": "Tara Westover"
    }
}

### 10.To get a particular book, use following url with `GET` request type in postman

  http://localhost:8080/api/books/<id>

### 11.To update Book in database, use following url with `PUT` request type in postman

	http://localhost:8080/api/books/<id>

### set content type as in Body as `raw/json`
### set request body as raw with JSON payload


 {
    "isbn": 145121891,
    "bookName": "Educated",
    "price": 50,
    "categories": "Memoir",
    "review": "A heartwrenching and inspiring memoir about a woman's journey from an isolated and abusive upbringing to earning a PhD from Cambridge University.",
    "author": {
        "aurthorid": 12,
        "authorName": "Tara Westover"
    }
}

### 12.To delete a particular Book from database, use following url with `DELETE` request type in postman

  http://localhost:8080/api/books/<id>

### 13.To get a particular aurthor, use following url with `GET` request type in postman

http://localhost:8080/api/author/<id>

### Note - Replace <id> with actual id 
