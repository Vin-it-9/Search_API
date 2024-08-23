# Makersharks Supplier Search API

## Overview

Makersharks is developing a proof of concept search API that allows buyers to find manufacturers based on customized criteria such as location, nature of business, and manufacturing processes.

## Prerequisites

Before getting started, ensure you have the following installed on your machine:

- **Java 17**: Required to run the application.
- **Maven 3.8.1 or higher**: Used for building and managing dependencies.
- **MySQL 8.0 or higher**: The database for storing and querying supplier data.
- **Spring Tool Suite (STS) or Visual Studio Code (VS Code)**: Recommended IDEs for running and debugging the project.

## Getting Started

### 1. Clone the Repository

Start by cloning the project repository to your local machine:

```bash
git clone https://github.com/Vin-it-9/Search_API.git
```

### 2. Open the Project in an IDE

To work with the project in an IDE, follow these steps:

1. Download and install either [Spring Tool Suite (STS)](https://spring.io/tools) or [Visual Studio Code (VS Code)](https://code.visualstudio.com/).
2. Open the cloned project in your chosen IDE.
3. Use the IDE's built-in tools to run and debug the project.

After running the project, the required database tables will be automatically created.


### 3. Set Up the Database

Create the `makersharks` database in MySQL:

```bash
CREATE DATABASE makersharks;
```

### 4. Configure Application Properties

Update the `application.properties` file located at `src/main/resources` to match your local database configuration:

```bash
spring.application.name=search-api

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/makersharks
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```


### 5. Load Raw Data

The project includes a SQL file containing raw data needed for testing. The file is located at `/main/resources/static/row_data.sql`. You can execute this file in your MySQL database using a MySQL client or by integrating it into the Spring Boot startup.

## Testing the `/api/supplier/query` Endpoint

You can test the `localhost:8080/api/supplier/query` endpoint with the following JSON request bodies. Use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/) for testing.

## Overview

This API allows you to search for suppliers based on various criteria including company name, website, location, nature of business, and manufacturing process.

## Request Body

The request body should be a JSON object with the following optional fields:

- `companyName` (String): The name of the company.
- `website` (String): The website URL of the company.
- `location` (String): The location of the company.
- `natureOfBusiness` (String): The nature of business (e.g., `small_scale`, `medium_scale`, `large_scale`).
- `manufacturingProcess` (String): The manufacturing process (e.g., `casting`, `coating`, `moulding`, `_3d_printing`).
- `page` (Integer): The page number for pagination (default is 0).
- `size` (Integer): The number of results per page (default is 20).

## Example Requests and Responses

### 1. Search by Company Name

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"companyName":"TechnoCrafts","page":0,"size":5}'
  ```
### 2. Search by Website

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"website":"www.technocrafts.in"}'
  ```
### 3. Search by Company Name and Website

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"companyName":"TechnoCrafts","website":"www.technocrafts.in","page":0,"size":10}'

  ```
  ### 4. Search with All Criteria

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"companyName":"TechnoCrafts","website":"www.technocrafts.in","location":"Bangalore","natureOfBusiness":"medium_scale","manufacturingProcess":"casting","page":0,"size":5}'
  ```
  ### 5. Search with only location

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"location":"Bangalore"}'
  ```
  ### 6. Search with location and natureOfBusiness

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"location":"Bangalore" , "natureOfBusiness":"medium_scale"}'
  ```
### 7. Search with page -1 and size 0

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"page":-1 , "size":0}'
  ```
  
### 8. Search with location and manufacturingProcess

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"location":"Bangalore" ,"manufacturingProcess":"casting"}'
  ```
### 9. Search incomplete companyName

**Request:**
```bash
curl -X POST "http://localhost:8080/api/supplier/query" \
     -H "Content-Type: application/json" \
     -d '{"companyName":"Techno"}'
  ```
  
  
    
