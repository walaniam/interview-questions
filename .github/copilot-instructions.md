# Copilot instructions for `interview-questions`

## Purpose of this repo

This is a collection of independent Java coding interview exercises, not a single
runtime application. Each exercise consists of a production stub and a
same-package JUnit 5 test that defines its contract. There are no shared
application layers or integration points between the exercises.

When solving an exercise, treat its test as the source of truth; Javadoc on the
production entry point supplies additional requirements.

## Build & test

- Java 21 (Temurin in CI), Maven. No wrapper — use system `mvn`.
- Build exactly as CI does (tests skipped): `mvn -B package -DskipTests --file pom.xml`
- Run all tests: `mvn test`
- Run a single test class: `mvn -Dtest=OrderStatisticsTest test`
- Run a single test method: `mvn -Dtest=OrderStatisticsTest#summarize_mixedStatuses test`
- CI (`.github/workflows/maven.yml`) only runs `package -DskipTests`; tests are not gated in CI, so run them locally before finishing a change.
- No lint or formatting command is configured.

## Architecture

- Exercises are grouped by difficulty in `walaniam.junior`,
  `walaniam.mid`, and `walaniam.senior`; tests mirror those packages under
  `src/test/java`.
- `junior/MyHashMap` is a from-scratch `java.util.Map<K,V>` implementation
  exercise.
- `mid/OrderStatistics` transforms `OrderTable` rows into
  `OrderSummaryTable` rows. `OrderTable`, `OrderSummaryTable`, and
  `OrderStatus` are the fixed data model shared by that exercise.
- `senior/TemperatureService` is a concurrency exercise. Its sensor contracts,
  temperature scale, and snapshot are nested types, and the test supplies
  delayed sensor implementations to verify the overall time budget.

## Repository conventions

- Keep production and test APIs unchanged. Tests deliberately use
  same-package access to package-private constructors, methods, nested
  interfaces, and value types; do not widen visibility or extract these types
  merely to make them accessible.
- Implement an exercise in its existing production class. Do not alter tests
  to accommodate an implementation.
- Preserve requirement Javadoc on exercise entry points such as
  `OrderStatistics#summarize` and `TemperatureService#readTemperature`.
- Available test libraries are JUnit Jupiter (including parameterized tests),
  AssertJ, and Awaitility. Follow the assertion style already used by the
  relevant test.
- Match the existing Java style: four-space indentation and explicit imports;
  no formatter is configured.

## Exercise-specific contracts

- `TemperatureService` must invoke sensors concurrently, return once at least
  two respond within one shared 500 ms budget, and throw `TimeoutException`
  otherwise. A sequential implementation cannot satisfy the timing cases.
- `OrderStatistics` excludes `CANCELLED` orders, groups by customer and status,
  and sorts by total revenue descending then customer name ascending.
- `MyHashMap` must implement `Map<K,V>` with its own buckets and resizing; do
  not delegate storage to `HashMap`.

## Non-code exercise material

`design/user_registration_candidate.md` is standalone interview-exercise prose,
not a specification for code under `src`; use it only when a task references it.
