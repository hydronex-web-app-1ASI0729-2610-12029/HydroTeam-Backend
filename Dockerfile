# Etapa 1: Compilación usando Java 26 y Maven
FROM maven:3.9.9-eclipse-temurin-26 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final ligera para ejecución con Java 26
FROM eclipse-temurin:26-jre-alpine
WORKDIR /app
COPY --from=build /app/target/tankiq-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
