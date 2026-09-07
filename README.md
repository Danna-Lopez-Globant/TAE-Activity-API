# TAE-Activity-API

API test automation for the **PerfDog** pet store, using the public [Swagger Petstore](https://petstore.swagger.io) API.

### Functionalities under test

| # | Functionality | Endpoint | Test class |
|---|---------------|----------|------------|
| 1 | Create a user | `POST /user` | `CreateUserTests` |
| 2 | Login with a newly created user | `GET /user/login` | `LoginUserTests` |
| 3 | List pets with status `available` | `GET /pet/findByStatus` | `GetAvailablePetsTests` |
| 4 | Get a specific pet by id | `GET /pet/{petId}` | `GetPetByIdTests` |
| 5 | Create an order for a pet | `POST /store/order` | `CreateOrderTests` |
| 6 | Logout | `GET /user/logout` | `LogoutUserTests` |

## Tech stack

- Java 8+
- Maven
- Rest Assured
- TestNG

## Project structure

```
src/test/java/com/globant/automation/
├── config/          # Base URL and API key setup
├── model/           # Request/response DTOs
├── request/         # Rest Assured request builder
├── test/            # Independent TestNG test classes
└── util/            # Test data factory
src/test/resources/
├── config.properties
└── testng.xml
```

## How to run

### All tests

```bash
mvn clean test
```

Surefire is configured to use `src/test/resources/testng.xml`.

### From IntelliJ

- Right-click `testng.xml` → **Run**
- Or run a single test class / method from the editor

## Design notes

- Each functionality has its own test class and at least one distinct test.
- Tests are independent: required preconditions (create user/pet) are done inside the same test when needed.
- `TestDataFactory` generates unique usernames, emails, passwords, and ids per run to avoid collisions.

## AI usage disclosure

Generative AI was used as support for:

- Writing and refining **code documentation** (Javadoc).
- **Corrections and improvements** to `TestDataFactory` (unique test data generation and factory structure).