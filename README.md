# DashboardApp

DashboardApp is a java application which provides different web services to manage applcaition dealing with Employee data related to differnet learning materials like Udemy, Cura etc

This project is based on the Spring Boot project and uses these packages :

# What's inside
```
Maven
Spring Core
Spring Cloud
Spring MVC (Tomcat)
Api Gateway
Eureka Server
```

## Installation
```
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies
```

## Database Configuration
Downlaod a MongoDb database and run in into your localhost server

```
spring.data.mongodb.host: 127.0.0.1
spring.data.mongodb.database: dashboard
```

## Usage
Run the project through the IDE and head out to ApiGateway API

```
http://localhost:8765/dashboard/v1/**
Eureka server : http://localhost:8010

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
@Fjaffri
