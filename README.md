# Backend-Dvelopment-Task
# User-age-api (Java / Spring Boot / MySQL)

A small RESTful API in **Java 21 + Spring Boot 3 + Spring Data JPA + MySQL 8 + Hibernate Validator + SLF4J/Logback + Flyway** that manages users with `name` and `dob`, and returns a dynamically computed `age` on read endpoints.

---

## Stack at a glance

| Concern             | Choice                                       | Why                                                                                       |
| ------------------- | -------------------------------------------- | ----------------------------------------------------------------------------------------- |
| Web framework       | **Spring Boot Web (Spring MVC)**             | The standard Java REST stack.                                                             |
| DB                  | **MySQL 8**                                  | Per project requirement.                                                                  |
| DB access           | **Spring Data JPA (Hibernate)** + JDBC       | CRUD + paging for free; easy to extend.                                                   |
| Validation          | **Hibernate Validator** (Jakarta Bean Validation) | Standard, declarative-annotation style.                                              |
| Logging             | **SLF4J + Logback** (Spring Boot default)    | Structured logs, `requestId` carried in MDC.                                              |
| Migrations          | **Flyway**                                   | Versioned schema, auto-runs on startup.                                                   |
| Build               | **Maven** (`pom.xml`)                        | The most common Java build tool.                                                          |

---

## Project layout

```
user-age-api-java/
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── src/
    ├── main/
    │   ├── java/com/example/userage/
    │   │   ├── UserAgeApiApplication.java         # @SpringBootApplication entry point
    │   │   ├── controller/
    │   │   │   ├── UserController.java            # /users CRUD
    │   │   │   └── HealthController.java          # /health
    │   │   ├── service/
    │   │   │   ├── UserService.java               # business rules + paging
    │   │   │   └── AgeCalculator.java             # pure age util (unit-tested)
    │   │   ├── repository/
    │   │   │   └── UserRepository.java            # Spring Data JPA
    │   │   ├── entity/
    │   │   │   └── UserEntity.java                # @Entity for `users`
    │   │   ├── dto/
    │   │   │   ├── CreateUserRequest.java
    │   │   │   ├── UpdateUserRequest.java
    │   │   │   ├── UserResponse.java
    │   │   │   └── PaginatedUsers.java
    │   │   ├── exception/
    │   │   │   ├── UserNotFoundException.java
    │   │   │   └── GlobalExceptionHandler.java    # 404/400/500 mapping
    │   │   └── filter/
    │   │       └── RequestIdFilter.java           # X-Request-Id + access log + MDC
    │   └── resources/
    │       ├── application.yml
    │       └── db/migration/V1__create_users.sql
    └── test/java/com/example/userage/service/
        └── AgeCalculatorTest.java                 # JUnit 5 tests for age calc
```

## Why this layout

- **Controller** is HTTP-only: route, validate (via `@Valid`), delegate, return.
- **Service** owns business rules — DTO ↔ entity mapping, age calculation, paging defaults, decides what fields appear in which response (per spec, POST/PUT responses don't include `age`; GET / list do).
- **Repository** is just `JpaRepository<UserEntity, Long>` — Spring Data generates CRUD, paging, sorting at runtime.
- **Entity** is the JPA mapping for the `users` table.
- **DTOs** are the API contract, separated from the entity so the JSON shape can evolve without DB changes.
- **`GlobalExceptionHandler`** centralizes HTTP error mapping so controllers don't repeat try/catch.
- **`RequestIdFilter`** propagates `X-Request-Id` from the client (or generates one), puts it in SLF4J's MDC so every log line for the request carries it, and logs `method`, `path`, `status`, `duration`.

## API contract

| Method | Path                          | Body              | Status | Notes                  |
| ------ | ----------------------------- | ----------------- | ------ | ---------------------- |
| POST   | `/users`                      | `{ name, dob }`   | 201    | response without `age` |
| GET    | `/users/{id}`                 | —                 | 200    | response with `age`    |
| PUT    | `/users/{id}`                 | `{ name, dob }`   | 200    | response without `age` |
| DELETE | `/users/{id}`                 | —                 | 204    | empty body             |
| GET    | `/users`                      | —                 | 200    | array (per spec)       |
| GET    | `/users?page=1&pageSize=20`   | —                 | 200    | paginated wrapper      |
| GET    | `/health`                     | —                 | 200    | liveness               |

`dob` is ISO `YYYY-MM-DD`. Validated with `@NotNull @Past`.

### Sample requests

```bash
# Create
curl -s -X POST localhost:8080/users \
  -H 'content-type: application/json' \
  -d '{"name":"Alice","dob":"1990-05-10"}'

# Get (returns age)
curl -s localhost:8080/users/1

# List (plain array)
curl -s localhost:8080/users

# List paginated
curl -s 'localhost:8080/users?page=1&pageSize=10'

# Update
curl -s -X PUT localhost:8080/users/1 \
  -H 'content-type: application/json' \
  -d '{"name":"Alice Updated","dob":"1991-03-15"}'

# Delete
curl -s -X DELETE -i localhost:8080/users/1
```

## Age calculation

`AgeCalculator.calculateAge(dob, today)` uses `java.time.Period.between(...)` which already handles month/day boundaries and leap years correctly. Future or null DOBs clamp to `0`. See `AgeCalculatorTest` for cases.

## Running locally

### Option A — Docker Compose (recommended)

```bash
docker compose up --build
```

Brings up MySQL 8 (db `user_age_db`, user `root` / pwd `root`) and the API on port 8080. Flyway runs `V1__create_users.sql` automatically on first connect.

### Option B — Maven + your own MySQL

Prereqs: JDK 21, Maven 3.9+, a running MySQL on `localhost:3306`.

```sql
-- create DB and user
CREATE DATABASE user_age_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

CREATE USER 'app'@'%' IDENTIFIED BY 'app';
GRANT ALL PRIVILEGES ON user_age_db.* TO 'app'@'%';
FLUSH PRIVILEGES;
```

```bash
# run with defaults (root/root):
mvn spring-boot:run

# or override the connection from the shell:
set DB_URL=jdbc:mysql://localhost:3306/user_age_db?useSSL=false^&serverTimezone=UTC^&allowPublicKeyRetrieval=true
set DB_USER=app
set DB_PASSWORD=app
mvn spring-boot:run

# tests
mvn test

# package
mvn -B -DskipTests package
java -jar target/user-age-api-0.1.0.jar
```

Configuration is read from environment variables (with defaults in `application.yml`):

| Var           | Default                                                                                                       |
| ------------- | ------------------------------------------------------------------------------------------------------------- |
| `APP_PORT`    | `8080`                                                                                                        |
| `DB_URL`      | `jdbc:mysql://localhost:3306/user_age_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=utf8&useUnicode=true` |
| `DB_USER`     | `root`                                                                                                        |
| `DB_PASSWORD` | `root`                                                                                                        |
| `LOG_LEVEL`   | `INFO`                                                                                                        |

> **JDBC URL flags explained.**
> - `useSSL=false` — disables SSL for local dev (turn it on for prod).
> - `serverTimezone=UTC` — keeps `DATE`/timestamp handling consistent; the JPA layer also has `jdbc.time_zone: UTC`.
> - `allowPublicKeyRetrieval=true` — required when MySQL 8 uses `caching_sha2_password` over a non-SSL connection.
> - `characterEncoding=utf8&useUnicode=true` — full Unicode support for names with accents/non-Latin characters.

## Validation & error handling

- `@Valid` on the request DTOs activates Hibernate Validator.
- `GlobalExceptionHandler` maps:
  - `MethodArgumentNotValidException` → `400 { error: "<field>: <msg>" }`
  - `HttpMessageNotReadableException` → `400 { error: "invalid JSON body" }`
  - `MethodArgumentTypeMismatchException` → `400 { error: "invalid path parameter: id" }`
  - `UserNotFoundException` → `404 { error: "user not found: id=<id>" }`
  - any other `Exception` → `500 { error: "internal error" }`
- Delete returns `204 No Content`.

## Logging

Logback pattern includes the MDC `requestId`:

```
2025-06-12 12:00:00.123 INFO  [9c3a...] c.e.u.s.UserService - user created id=1
```

Every HTTP request also produces a one-liner from `RequestIdFilter`:

```
http_request method=POST path=/users status=201 duration_ms=18
```

## Tests

```
mvn test
```

`AgeCalculatorTest` covers: birthday already passed, birthday today, birthday later this year, future DOB, null inputs.

## Submission

```bash
git init
git add .
git commit -m "feat: user-age-api (java)"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```
