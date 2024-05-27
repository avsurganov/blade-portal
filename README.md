# Blades in the Dark API

This project is an API for the game "Blades in the Dark". The API provides access to playbooks, abilities, and other game-related data. It was made for [ZelenskaD](https://github.com/ZelenskaD) for Springboard's Capstone Project 1.

## Features

- Access to all playbooks and their special abilities
- Detailed Swagger API documentation
- CORS enabled for cross-origin requests

## Setup and Run

### Prerequisites

- Java 17 or higher
- sbt 1.9.7 or higher

### Running the Application

1. **Compile the application:**

```
$ sbt clean compile
```

2. **Run the application:**

```
$ sbt run
```

3. **Access the API:**

    - **Swagger UI:** <https://blades-portal-api.surganov.dev/swagger>
    - **Swagger JSON:** <https://blades-portal-api.surganov.dev/swagger.json>

## API Endpoints

- **GET /api/playbooks**: Retrieve all playbooks
- **GET /api/playbooks/{name}**: Retrieve a specific playbook by name

## Documentation

The API documentation is provided using Swagger. You can access the Swagger UI at <https://blades-portal-api.surganov.dev/swagger>.

## Contact

For any issues or contributions, please visit the [GitHub repository](https://github.com/avsurganov/blade-portal-api/issues) or contact [Vlad Surganov](https://www.linkedin.com/in/avsurganov/).

## License

This project is licensed under the Creative Commons Attribution 4.0 Unported License. See the [LICENSE](https://creativecommons.org/licenses/by/4.0/) file for details.
