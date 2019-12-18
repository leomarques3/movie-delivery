# Movie Delivery Application
Let you rent movies online.

## 1 - Stack
### 1.1 - Installation
Artifacts | Version
--------- | ---------
Gradle    | 5.6.4
Java JDK  | 11.0.4
MySql     |

## 2 - Usage
### 2.1 - Useful tasks
Task                          | Notes
----------------------------- | ---------------------------------------
gradle build                  | Compile the code and generates the jar.
gradle build jacocoTestReport | Generates the test report.
gradle bootRun                | Starts the application.

### 2.2 - Operations
Operation            | Path                 | Requires auth?
-------------------- | -------------------- | --------------
Login                | /auth/login          | Basic
Sign Up              | /auth/signUp         |
Get Available Movies | /movies/available    | Bearer token
Get Movie by Title   | /movies/find/{title} | Bearer token
Rent a Movie by id   | /movies/rent/{id}    | Bearer token
Return a Movie by id | /movies/return/{id}  | Bearer token

### 2.3 - Instructions
1. Have the mysql server installed.
2. Run the script.sql inside the project folder with the command 'source script.sql' using the mysql command line interface.
3. Have a jdk and gradle installed.
5. Clone the project.
6. Execute the command 'gradle bootRun' in the modules 'auth-server', 'movie-management-api' and 'documentation-server'.

### 2.4 - Specification
_The api specification using swagger2 will be available at:_ ```localhost:8089/swagger-ui.html```

## 3 - Features
### 3.1 - Login
- Receives the basic auth in the authorization header.
- Search for the user in the database.
- Authenticate the user.
- Generates the jwt token in the authorization header.

### 3.2 - Sign Up
- Receives the request body with the information of the new user.
- Save the data in the database.

### 3.3 - Get Available Movies
- Receives the bearer token to authorize.
- Search for all available movies in the database.
- Generates a json response with the information.

### 3.4 - Get Movie by Title
- Receives the bearer token to authorize.
- Search for the movie(s) using the given title.
- Generates a json response with the information.

### 3.5 - Rent a Movie by id
- Receives the bearer token to authorize.
- Search for the movie using the given id.
- Update its status to rented and set the current user to it.

### 3.6 - Return a Movie by id
- Receives the bearer token to authorize.
- Search for the movie using the given id.
- Update its status to available and set the user to null.

## 4 - Technical debt
- Implement log generation