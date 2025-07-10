# LaplateformeTracker

LaplateformeTracker is a JavaFX application for managing and tracking students in a class (add, edit, delete, search, statistics, JSON export/import, user authentication, etc.).

## Project Structure

```
LaplateformeTracker
├── build
├── docs
│   └── Diagramme sans nom.drawio.png
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── App.java
│   │   │   ├── Database.java
│   │   │   ├── HashUtil.java
│   │   │   ├── JsonManager.java
│   │   │   ├── MenuController.java
│   │   │   ├── StatisticsController.java
│   │   │   ├── Student.java
│   │   │   ├── StudentController.java
│   │   │   ├── User.java
│   │   │   ├── UserController.java
│   │   │   ├── CreateUserController.java
│   │   │   └── LoginController.java
│   │   └── resources
│   │       ├── add_student.fxml
│   │       ├── create_user.fxml
│   │       ├── login.fxml
│   │       ├── menu.fxml
│   │       ├── statistics.fxml
│   │       └── students.json
│   └── test
│       └── java
│           └── DatabaseTest.java
├── .env
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## Project Purpose

The application allows:
- Student management (CRUD)
- Search and filtering (name, age, grade)
- Display of statistics (total number, age groups, average grade)
- Export/import of the student list in JSON format
- User authentication and account creation
- A modern and ergonomic interface (JavaFX)

## Setup Instructions

1. **Clone the repository**
   ```
   git clone <repository-url>
   cd LaplateformeTracker
   ```

2. **Configure the `.env` file**
   - Fill in your PostgreSQL database connection info:
     ```
     DB_HOST=localhost
     DB_PORT=5432
     DB_NAME=laplateformetracker
     DB_USER=postgres
     DB_PASSWORD=yourpassword
     ```

3. **Install dependencies**
   ```
   ./gradlew build
   ```
   (Gradle will automatically download all required dependencies)

4. **Run the application**
   ```
   ./gradlew run
   ```

## Running Tests

To run the unit tests:
```
./gradlew test
```

## Main Dependencies
- JavaFX
- Jackson (for JSON)
- JUnit (tests)
- PostgreSQL JDBC