# TankIQ API

Spring Boot base project migrated from `learning-center-platform` to the TankIQ domain.

## Bounded Contexts

- `iam` → `User`
- `monitoring` → `Cistern`
- `refill` → `Refill`
- `notification` → `Alert`
- `reporting` → `Report`
- `subscription` → `Subscription`
- `shared` → common infrastructure, result handling, OpenAPI and REST helpers

## Local database

```bash
docker compose up -d
```

MySQL will be available at `localhost:3306` with:

- database: `tankiq-12029`
- username: `root`
- password: `root`

## Run locally

```bash
./mvnw spring-boot:run
```

Swagger UI: `http://localhost:8080/swagger-ui.html`
