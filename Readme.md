```markdown
# 🎬 Movie Database REST API

A **RESTful API** built with **Spring Boot**, **Spring Data MongoDB**, and **Lombok** for managing movies, actors, and directors.

The API allows you to:
- **Create**, **Read**, **Update**, and **Delete** movies, actors, and directors
- Manage relationships between movies, actors, and directors
- Search for movies by title or release date
- Search for actors/directors by name
- Return clean API responses using **DTOs**
- Unified error handling with a global exception handler
- Basic unit and integration testing with **JaCoCo coverage reports**

---

## 📌 Tech Stack

- **Java 17+**
- **Spring Boot 3+**
- **Spring Web**
- **Spring Data MongoDB**
- **Lombok**
- **JUnit 5** & **Mockito** for testing
- **JaCoCo** for test coverage reporting
- **Maven** build tool
- **MongoDB** (local or MongoDB Atlas cloud)

---

## ✨ Features

- CRUD endpoints for:
  - Movies
  - Actors
  - Directors
- DTO-based request/response handling
- Search endpoints for title/date/name
- Global exception handling returning structured JSON errors
- High test coverage (80%+ with JaCoCo)

---

## 📂 Project Structure

```
src/
├── main/
│    ├── java/com/example/movieapi/
│    │    ├── controller/      # REST Controllers
│    │    ├── service/         # Business logic
│    │    ├── repository/      # Spring Data Mongo Repositories
│    │    ├── model/           # MongoDB entity models
│    │    ├── dto/             # DTO classes
│    │    ├── mapper/          # DTO-to-Entity mappers
│    │    ├── exception/       # Custom exceptions & global handler
│    │    └── MovieApiApplication.java # Spring Boot entry point
│    └── resources/
│         └── application.properties  # MongoDB config
└── test/java/com/example/movieapi/    # Full test suite
```

---

## ⚙️ Configuration

### **1. MongoDB Connection**
In `src/main/resources/application.properties`:

#### Local:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=movie_db
```

#### MongoDB Atlas:
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/<dbname>?retryWrites=true&w=majority
```

---

## 🚀 Running the Application

### **1. Clone Repository**
```bash
git clone https://github.com/yourusername/movie-api.git
cd movie-api
```

### **2. Build the Application**
```bash
mvn clean install
```

### **3. Run with Maven**
```bash
mvn spring-boot:run
```

### **4. Or Run JAR**
```bash
java -jar target/movie-api-0.0.1-SNAPSHOT.jar
```

The API will be available at:
```
http://localhost:8080
```

---

## 🧪 Running Tests & Checking Coverage

Run the test suite:
```bash
mvn test
```

Generate coverage report:
```bash
mvn clean verify
```

Open report:
```
target/site/jacoco/index.html
```

---

## 📡 API Endpoints

### **Movies**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/movies | List all movies |
| GET    | /api/movies/{id} | Get a movie by ID |
| POST   | /api/movies | Create a new movie |
| PUT    | /api/movies/{id} | Update a movie |
| DELETE | /api/movies/{id} | Delete a movie |
| GET    | /api/movies/search/title?title=XYZ | Search movies by title |
| GET    | /api/movies/search/releaseDate?date=YYYY-MM-DD | Search movies by release date |

### **Actors**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/actors | List all actors |
| GET    | /api/actors/{id} | Get an actor by ID |
| POST   | /api/actors | Create a new actor |
| PUT    | /api/actors/{id} | Update an actor |
| DELETE | /api/actors/{id} | Delete an actor |
| GET    | /api/actors/search?name=Name | Search actors by name |

### **Directors**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/directors | List all directors |
| GET    | /api/directors/{id} | Get a director by ID |
| POST   | /api/directors | Create a new director |
| PUT    | /api/directors/{id} | Update a director |
| DELETE | /api/directors/{id} | Delete a director |
| GET    | /api/directors/search?name=Name | Search directors by name |

---

## 🔐 Error Handling

All errors return structured JSON responses. Example for 404 Not Found:
```json
{
  "timestamp": "2024-06-12T12:34:56.789",
  "status": 404,
  "error": "Not Found",
  "message": "Movie not found with id abc123"
}
```

---

## 💡 Next Steps / Roadmap
- Add **pagination & sorting**
- Implement **JWT-based authentication**
- Generate **Swagger/OpenAPI documentation**
- Deploy to **Heroku**, **Render** or **AWS**

---

## 👨‍💻 Author
**Your Name** – [Your GitHub Profile](https://github.com/yourusername)
```

---

### 📌 Notes:
- You can replace `yourusername` in the `git clone` and author section with your actual GitHub username if you’re putting this on GitHub.
- If you want, I can **add Swagger/OpenAPI** next so your documentation includes a live API explorer in the browser.

---
