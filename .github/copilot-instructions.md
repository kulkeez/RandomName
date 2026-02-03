# Copilot Instructions for RandomName Generator

## Project Overview

**RandomName** is a Spring Boot 4.0 REST API that generates pseudo-random names by combining adjectives and nouns. It demonstrates a complete containerization and Kubernetes deployment pipeline: Java → Docker → Kubernetes.

**Stack**: Spring Boot 4.0, Java 21, Maven 3.9, H2 database, Docker, Kubernetes (KinD)
**Key Libraries**: Spring Data JPA, Lombok, SLF4J, JUnit 5, AssertJ

## Architecture & Key Components

### Three-Tier Structure
- **Controllers** (`controller/`): REST endpoints mapped to `/api/v1/*` 
  - `EmployeeController`: Handles `/api/v1/employees` GET requests
  - `HelloController`: Serves random name greetings via Actuator integration
  
- **Services** (`service/`): Business logic with dependency injection
  - `EmployeeService`: Retrieves employees via repository layer
  
- **Repository** (`repository/`): Spring Data JPA interfaces extending `JpaRepository`
  - `EmployeeRepository`: Auto-generates DB queries for `Employee` entity

- **Models** (`model/`): JPA entities using Jakarta Persistence (not javax)
  - `Employee`: H2-persisted entity with Lombok `@Data` annotations

### Core Generation Logic
- **`RandomNameGenerator`**: Spring `@Component` that generates unique names from dictionary
  - Uses prime number algorithm to cycle through ~1.5M unique combinations
  - Thread-safe with synchronized access via `randomName()` method
  - Initialized with seed (defaults to `System.currentTimeMillis()`)

- **`Dictionary`**: Singleton pattern providing adjectives and nouns
  - Loads from text files: `src/main/resources/com/kulkeez/{a.txt, n.txt, v.txt}`

## Build & Deployment Workflow

### Maven Commands
```bash
mvn clean compile package -DskipTests    # Compile and package JAR
mvn spring-boot:run                       # Run locally on port 8080
mvn test                                  # Run unit tests
```

### Docker Workflow (see `build_docker_image.sh`)
```bash
docker build -t kulkeez/random-name:0.1 .  # Creates image from Dockerfile
docker run -d -p 8080:8080 <image-id>      # Run container
docker ps                                   # Verify container running
```

### Kubernetes Deployment (see `create_kind_cluster.sh` and `deploy_pod.sh`)
```bash
kind create cluster --image kindest/node:v1.25.3 --name dev-kind
kubectl create -f deploy-pod.yaml
kubectl port-forward pod/random-name-pod 8080
```

## Project Conventions

### Lombok Usage
All model classes use Lombok annotations:
- `@Data`: Auto-generates getters, setters, equals, hashCode, toString
- `@AllArgsConstructor` / `@NoArgsConstructor`: Constructors
- `@Slf4j`: Static logger field named `log`

### Constructor Injection (NOT @Autowired)
Prefer constructor injection for explicit dependencies. Controllers and services use constructor parameters:
```java
public EmployeeController(EmployeeService service) {
    this.service = service;
}
```
This pattern improves testability—see `EmployeeControllerTest` for testing with stub services.

### Test Patterns
- **Unit Tests**: Use manual object construction and stub services (see `EmployeeControllerTest`)
- **Integration Tests**: Use Spring `GenericApplicationContext` for lightweight Spring setup
- **Stubs Over Mocks**: Create anonymous inner classes extending service interfaces rather than mocking frameworks
- Framework: JUnit 5 with AssertJ assertions

### REST Conventions
- Base path: `/api/v1/`
- All endpoints are GET with path mappings
- Controllers use `@RestController` and `@RequestMapping` class-level annotations

## Database

**H2 Embedded Database** (in-memory during dev, persisted in prod)
- Configuration: `src/main/resources/application.properties`
- Schema: Single `EMPLOYEE` table with fields: id, name, age, gender, DOB, join date, department
- Data initialization: `src/main/resources/data.sql` (SQL insert statements)
- Console: Accessible at `http://localhost:8080/h2-console` during runtime

## Key Files Reference

| File | Purpose |
|------|---------|
| [pom.xml](pom.xml) | Maven POM with Spring Boot parent, dev/prod profiles |
| [Dockerfile](Dockerfile) | Multi-stage build using `eclipse-temurin:21` |
| [application.properties](src/main/resources/application.properties) | Server (8080), Actuator endpoints, H2 config |
| [RandomNameApplication.java](src/main/java/com/kulkeez/RandomNameApplication.java) | Spring Boot app entry point with CommandLineRunner |
| [RandomNameGenerator.java](src/main/java/com/kulkeez/RandomNameGenerator.java) | Core generation logic |

## Actuator Endpoints

Spring Boot Actuator exposes monitoring endpoints (enabled in `application.properties`):
- `/actuator/health` — Application health status
- `/actuator/info` — App metadata (version, programmer, etc.)
- `/actuator/metrics` — Performance metrics
- `/actuator/mappings` — Registered request mappings
- `/actuator/env` — Environment properties

## Testing Strategy

1. **Unit tests** (preferred): Test components in isolation with manual stubs
2. **Integration tests**: Use `GenericApplicationContext` for minimal Spring context
3. **Smoke tests**: Basic context load tests to detect autowiring issues

When modifying controllers or services, create constructor-injectable stubs by extending service interfaces with anonymous classes.

## Common Pitfalls to Avoid

- **Don't use `@Autowired`** on fields; use constructor injection instead
- **Don't use Mockito**; create stub implementations as inner classes
- **Don't reference `javax.persistence`**; this project uses Jakarta Persistence (jakarta.persistence.*)
- **Java 17 features**: Use records sparingly; project favors Lombok `@Data` for POJOs
