# Spring Boot: Pokemon Rest API

## Steps to Setup

**1. Create database pokemon**
```bash
create database pokemon
```

**2. Setting application port, username, password mysql**

Directory file: src/main/resources/application.properties

**3. Run application:**
```bash
.\mvnw spring-boot:run
```


##### If using VSCode, download extentions [Lombok Annotations Support for VS Code](https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok) to make VSCode recognize lombok annotation 


### A few of the essential features for consuming an API are:

**1. Default value and return all data pokemon from API**
http://localhost:8181/api/pokemon/search

Search with pagination
http://localhost:8181/api/pokemon/search?page=0&size=20

**2. Pagination with sorting**
http://localhost:8181/api/pokemon/search?page=0&size=10&sort=type

Sorting by type and asc
http://localhost:8181/api/pokemon/search?page=0&size=10&sort=type,asc

Sorting by multiple param
http://localhost:8181/api/pokemon/search?page=0&size=10&sort=type,id,asc

**3. Filtering, filtering where keyword contains in name and type**
http://localhost:8181/api/pokemon/search/filter?query=fire&page=0&size=10

Filtering and sorting
http://localhost:8181/api/pokemon/search/filter?query=fire&page=0&size=10&sort=type,id,asc


### For more information about API and try update, delete API. Accesss Swagger UI and click button Try it out at each method
http://localhost:8181/swagger-ui/#