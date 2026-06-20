# TankIQ API

Spring Boot backend project.

## Architecture

The project is organized using Domain-Driven Design (DDD) concepts and is divided into independent Bounded Contexts.

## Bounded Contexts

- `iam` → `User`
- `monitoring` → `Cistern`
- `refill` → `Refill`
- `notification` → `Alert`
- `reporting` → `Report`
- `subscription` → `Subscription`
- `shared` → Common infrastructure, result handling, OpenAPI configuration and REST helpers

## Project Structure

```text
src/main/java/com/tankiq
│
├── iam
├── monitoring
├── refill
├── notification
├── reporting
├── subscription
└── shared
```

Each bounded context follows the same structure:

```text
domain
application
infrastructure
interfaces
```

## Development Workflow

### Main Branches

- `main` → Stable branch
- `develop` → Integration branch

## Local Database

Start MySQL using Docker:

```bash
docker compose up -d
```

MySQL local configuration:

- Database: `tankiq-12029`
- Username: `root`
- Password: `root`
- Port: `3306`

## Run Locally

```bash
./mvnw spring-boot:run
```

Local Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

## Deployment

### Backend

https://hydroteam-backend.onrender.com

### Swagger UI

https://hydroteam-backend.onrender.com/swagger-ui.html

### OpenAPI Specification

https://hydroteam-backend.onrender.com/v3/api-docs

## Infrastructure

- Backend Hosting: Render
- Database Hosting: Railway
- Database Engine: MySQL

## Current Status

| Bounded Context | Main Aggregate |
|----------------|----------------|
| IAM | User |
| Monitoring | Cistern |
| Refill | Refill |
| Notification | Alert |
| Reporting | Report |
| Subscription | Subscription |


