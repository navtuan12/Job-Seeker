FROM maven:3.9.2-eclipse-temurin-17 as builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests



FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/*.jar /app/run.jar
EXPOSE 8800

ENTRYPOINT ["java", "-jar", "/app/run.jar"]