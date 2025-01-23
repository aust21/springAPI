# Recycling Tips API

This project is a Spring Boot application designed to manage recycling tips, waste guidlines. The API provides functionality to add, update, retrieve, and delete data, RESTful endpoints.

## Features

- Add new data (recycling tips, waste guidelines and categories).
- Retrieve a data.
- Retrieve a specific data entry by ID.
- Update an existing data entry by ID.
- Delete a data entry by ID.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **H2 Database**: In-memory database for development and testing.
- **JUnit 5**: Unit testing framework.
- **Mockito**: Mocking framework for testing.
- **Maven**: Dependency and build management.

---

## Setup Instructions

### Prerequisites

1. Install Java 17 or later.
2. Install Maven.
3. Clone the repository:
   ```bash
   git clone https://github.com/aust21/springAPI.git
   cd springAPI
   ```

### Run the Application

1. Build the project:
   ```bash
   mvn clean install
   ```
2. Start the application:
   ```bash
   mvn spring-boot:run
   ```
3. Access the H2 database console at: [http://localhost:8080/h2](http://localhost:8080/h2)

### Database Configuration

The application uses the following database configuration:

```properties
spring.datasource.url=jdbc:h2:mem:database
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=grad001
spring.datasource.password=austin
```

---

## Endpoints

### Recycling tips

`http://localhost:8080/rec/`

### API Endpoints

#### Add a Recycling Tip

- **POST** `/add`
- **Request Body**:
  ```json
  {
    "id": 1,
    "recyclingTip": "Reduce, Reuse, Recycle"
  }
  ```

#### Get All Recycling Tips

- **GET** `/get`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "recyclingTip": "Reduce, Reuse, Recycle"
    }
  ]
  ```

#### Get a Recycling Tip by ID

- **GET** `/get/{id}`
- **Response**:
  ```json
  {
    "id": 1,
    "recyclingTip": "Reduce, Reuse, Recycle"
  }
  ```

#### Update a Recycling Tip by ID

- **PUT** `/update/{id}`
- **Request Body**:
  ```json
  {
    "recyclingTip": "Updated Tip"
  }
  ```

#### Delete a Recycling Tip by ID

- **DELETE** `/delete/{id}`
- **Response**: HTTP 200 OK or HTTP 404 Not Found

### Waste Categories
`http://localhost:8080/cat/`
- All endpoints are the same as above

### Waste Guidelines
`http://localhost:8080/guide/`
- All endpoints are the same as above
---



## Testing

### Unit Tests

Unit tests are implemented using **JUnit 5** and **Mockito**.

To run the tests:

```bash
mvn test
```

---

**Author:** Austin Ngobeni

