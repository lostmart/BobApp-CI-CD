# Multi-stage build for Spring Boot backend (Java 11)
FROM eclipse-temurin:11-jdk AS build

# Set working directory
WORKDIR /workspace

# Copy pom.xml first for better dependency caching
COPY pom.xml .

# Download dependencies (cached if pom.xml unchanged)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests -B

# Production stage
FROM eclipse-temurin:11-jre AS production

WORKDIR /app

# Copy jar file
COPY --from=build /workspace/target/*.jar app.jar

# Security: non-root user
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown appuser:appuser /app
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]