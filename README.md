# interview-questions

A small collection of self-contained Java coding exercises used in technical
interviews. Each exercise is independent — there is no shared application to
run. You implement a single class; a JUnit 5 test verifies the contract.

## Before you start

- **Java 21** and **Maven** are required.
- Build (no tests): `mvn -B package -DskipTests`
- Run all tests: `mvn test`
- Run one class: `mvn -Dtest=OrderStatisticsTest test`
- Run one method: `mvn -Dtest=OrderStatisticsTest#summarize_mixedStatuses test`

## Ground rules

- The **test is the source of truth**. Read it together with the Javadoc on the
  method you are implementing.
- **Do not modify the tests.** If a test seems wrong, ask.
- **Do not change public/package visibility** of existing types, methods, or
  constructors. Tests use same-package access on purpose.
- Implement the solution inside the **existing production class**. Do not move
  or rename it.
- Standard JDK is available. Test-side libraries already on the classpath:
  JUnit Jupiter (incl. parameterized), AssertJ, Awaitility.

## Exercises

| Level  | Exercise            | What it's about                              | Details |
|--------|---------------------|----------------------------------------------|---------|
| Junior | `MyHashMap`         | Implement a `java.util.Map`                  | [src/main/java/walaniam/junior/README.md](src/main/java/walaniam/junior/README.md) |
| Mid    | `OrderStatistics`   | Produce a summary from a table of orders     | [src/main/java/walaniam/mid/README.md](src/main/java/walaniam/mid/README.md) |
| Senior | `TemperatureService`| Return a temperature reading from 3 sensors  | [src/main/java/walaniam/senior/README.md](src/main/java/walaniam/senior/README.md) |

Pick the exercise you were asked to do and open its README first.
