# Stock Management Application
Develop a stock management application for a company selling computer products.

## Technology Stack
- Spring Boot
- Gradle
- Database (H2 for testing)
- Java 8

## Compatible IDE
- Netbeans
    - Install Gradle Plugin for Netbeans.
- Intellij
- Eclipse

## Package Anatomy

### com.stock.mgmt.controllers.*
Use this to integrate with your front-end, 
these controllers use spring-web to expose endpoints to clients.

### com.stock.mgmt.entities.*
In this package you will see Business Entities like Product and Brand. 
These entities are mapped directly into database using spring-data.

### com.stock.mgmt.repositories.*
In this package you will see classes that talks to database, 
they handle low level orchestration of communication to database.

### com.stock.mgmt.services.*
In this package you will see classes that deals with business flows, 
this is the place to orchestrate business flows. This classes
talks to its corresponding repository to fetch data from db. 

## Project Configuration
You may check on application.properties file for the default application config.
You may add environment specific config if needed e.g. application-prod.properties

## API Documentation
I have provided postman documentation, you may visit this link to view interactive API documentation of this project.
The API doc also provides you ways to integrate using many other scripting/programming languages like curl,java,c#.
https://documenter.getpostman.com/view/2693913/TVenfp4E


## H2 DB for testing only

 - local console access url: http://localhost:8080/console/
 - db: jdbc:h2:mem:testdb
 - username: sa
 - password:
 
## In case app did not stop force it
 - sudo lsof -i :8080
 - kill -9 PID

## Authors
- Mark Anthony Ortiz - ortizmark905@gmail.com
