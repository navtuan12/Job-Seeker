FROM maven:3.9.2-eclipse-temurin-17 as builder

# Set the working directory for the build process
WORKDIR /app

# Copy the pom.xml and source code into the Docker image
COPY pom.xml .
COPY src ./src
# COPY create-env.sh ./
# RUN chmod +x create-env.sh
# RUN ./create-env.sh

# Package the Spring Boot application (skipping tests to save time)
RUN mvn clean package -DskipTests

# Start from an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Add the JAR file from the host machine to the image
COPY --from=builder /app/target/*.jar /app/run.jar

# Expose the port the application runs on (usually 8080 for Spring Boot)
EXPOSE 8800

# Run the application
ENTRYPOINT ["java", "-jar", "/app/run.jar"]