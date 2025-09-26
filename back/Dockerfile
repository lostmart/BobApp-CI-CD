# Multi-stage build for Spring Boot backend (Java 11)
FROM eclipse-temurin:11-jdk AS build

# Set working directory
WORKDIR /workspace

# Copy pom.xml first for better caching
COPY pom.xml /workspace/

# Copy Maven wrapper if it exists
COPY .mvn /workspace/.mvn/
COPY mvnw* /workspace/

# Download dependencies
RUN if [ -f "./mvnw" ]; then \
      chmod +x ./mvnw && ./mvnw dependency:go-offline -B; \
    else \
      mvn dependency:go-offline -B; \
    fi

# Copy source code
COPY src /workspace/src/

# Build the application
RUN if [ -f "./mvnw" ]; then \
      ./mvnw clean package -DskipTests -B; \
    else \
      mvn clean package -DskipTests -B; \
    fi

# Production stage with Java 11 runtime
FROM eclipse-temurin:11-jre AS production

# Set working directory
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /workspace/target/*.jar app.jar

# Create non-root user for security
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown appuser:appuser /app
USER appuser

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
