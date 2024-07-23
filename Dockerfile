# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the project
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
