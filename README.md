# My Java Gradle Project

This project is a simple Java application built using Gradle. It demonstrates the basic structure of a Java project and includes a main application class along with a test class.

## Project Structure

```
my-java-gradle-project
├── src
│   ├── main
│   │   └── java
│   │       └── App.java
│   └── test
│       └── java
│           └── AppTest.java
├── build.gradle
├── settings.gradle
└── README.md
```

## Getting Started

To build and run the application, follow these steps:

1. **Clone the repository** (if applicable):
   ```
   git clone <repository-url>
   cd my-java-gradle-project
   ```

2. **Build the project**:
   ```
   ./gradlew build
   ```

3. **Run the application**:
   ```
   ./gradlew run
   ```

## Running Tests

To run the tests, use the following command:
```
./gradlew test
```

## Dependencies

This project uses Gradle for dependency management. You can add dependencies in the `build.gradle` file as needed.