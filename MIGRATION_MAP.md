# TankIQ migration map

This project was migrated from the `learning-center-platform` base project.

## Package migration

| Source | Target |
|---|---|
| `com.learning.center.platform` | `com.tankiq` |
| `LearningCenterPlatformApplication` | `TankiqApplication` |
| `learning-center-platform` | `tankiq` |

## Bounded Contexts

| Bounded Context | Package | Main Aggregate Root | Endpoint |
|---|---|---|---|
| IAM | `iam` | `User` | `/api/v1/users` |
| Monitoring | `monitoring` | `Cistern` | `/api/v1/cisterns` |
| Refill Management | `refill` | `Refill` | `/api/v1/refills` |
| Notification | `notification` | `Alert` | `/api/v1/alerts` |
| Reporting | `reporting` | `Report` | `/api/v1/reports` |
| Subscription & Billing | `subscription` | `Subscription` | `/api/v1/subscriptions` |
| Shared Kernel | `shared` | Common infrastructure | N/A |

## DB JSON mapping

| DB JSON collection | Bounded Context | Notes |
|---|---|---|
| `users` | IAM | Main aggregate: `User` |
| `buildings` | IAM / referenced from other BCs | Kept as `buildingId` reference in this base version |
| `user_buildings` | IAM | Candidate child entity for a later iteration |
| `cisterns` | Monitoring | Main aggregate: `Cistern` |
| `sensors` | Monitoring | Candidate child entity for a later iteration |
| `water_level_readings` | Monitoring | Candidate child entity for a later iteration |
| `water_consumptions` | Monitoring | Candidate child entity for a later iteration |
| `refills` | Refill Management | Main aggregate: `Refill` |
| `alerts` | Notification | Main aggregate: `Alert` |
| `reports` | Reporting | Main aggregate: `Report` |
| `plans` | Subscription & Billing | Candidate entity/reference for a later iteration |
| `subscriptions` | Subscription & Billing | Main aggregate: `Subscription` |
