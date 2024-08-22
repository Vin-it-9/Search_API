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

\```bash
git clone https://github.com/Vin-it-9/Search_API.git
\```

### 2. Set Up the Database

Create the `makersharks` database in MySQL:

\```sql
CREATE DATABASE makersharks;
\```

### 3. Configure Application Properties

Update the `application.properties` file located at `src/main/resources` to match your local database configuration:

\```properties
spring.application.name=search-api

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/makersharks
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
\```

### 4. Load Raw Data

The project includes a SQL file containing raw data needed for testing. The file is located at `/main/resources/static/row_data.sql`. You can execute this file in your MySQL database using a MySQL client or by integrating it into the Spring Boot startup.

### 5. Build and Run the Project

Navigate to the project directory and build the project using Maven:

\```bash
mvn clean install
\```

After the build is complete, run the project:

\```bash
mvn spring-boot:run
\```

### 6. Open the Project in an IDE

To work with the project in an IDE, follow these steps:

1. Download and install either [Spring Tool Suite (STS)](https://spring.io/tools) or [Visual Studio Code (VS Code)](https://code.visualstudio.com/).
2. Open the cloned project in your chosen IDE.
3. Use the IDE's built-in tools to run and debug the project.

## Testing the `/api/supplier/query` Endpoint

You can test the `/api/supplier/query` endpoint with the following JSON request bodies. Use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/) for testing.

### 1. Test Case: Fetch Small Scale Manufacturers in Pune with 3D Printing Capability

**Request:**

\```json
{
  "location": "Pune",
  "natureOfBusiness": "small_scale",
  "manufacturingProcesses": "_3d_printing",
  "page": 0,
  "size": 5
}
\```

### 2. Test Case: Fetch All Manufacturers in Mumbai

**Request:**


---bash
{
  "location": "Mumbai",
  "page": 0,
  "size": 5
}
\```

### 3. Test Case: Fetch All Manufacturers with Casting Capability

**Request:**

\```json
{
  "manufacturingProcesses": "casting"
}
\```

## Additional Information

For more details, refer to the code comments and documentation within the project. The project structure is organized to support easy expansion and customization, ensuring flexibility in implementing additional features in the future.
