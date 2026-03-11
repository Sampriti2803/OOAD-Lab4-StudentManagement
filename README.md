# Student Management System

A comprehensive student management system built with Spring Boot, featuring CRUD operations, student management, and a modern web interface.

## Features

- **Student Management**: Add, view, update, and delete student records.
- **Web Interface**: User-friendly interface built with Thymeleaf.
- **Data Persistence**: Data is stored in an H2 in-memory database.
- **RESTful API**: Exposes REST endpoints for student operations.

## Tech Stack

- **Framework**: Spring Boot 2.7.18
- **Language**: Java 17
- **Database**: H2 In-Memory Database
- **Templating Engine**: Thymeleaf
- **Build Tool**: Maven

## Prerequisites

- **Java 17** or higher
- **Maven 3.6** or higher

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Sampriti2803/OOAD-Lab4-StudentManagement.git
   cd OOAD-Lab4-StudentManagement
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

## Usage

1. Run the application:
   ```bash
   mvn spring-boot:run
   ```

2. Access the web interface:
   Open your browser and navigate to: `http://localhost:8080`

## Project Structure

```
src/main/java/com/lab/studentmanagement/
├── controller/        # Web controllers
├── model/             # Domain models
├── repository/        # Data access layer
├── service/           # Business logic
└── StudentManagementApplication.java  # Main application class

src/main/resources/
├── static/            # CSS and JavaScript files
├── templates/         # Thymeleaf templates
└── application.properties  # Application configuration
```

## Database

The application uses an H2 in-memory database, which means:
- Data is stored in memory and will be lost when the application restarts.
- No database setup is required.
- The database console is available at `http://localhost:8080/h2-console` (username: `sa`, password: empty).

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get a student by ID |
| POST | `/students` | Create a new student |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete a student |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.