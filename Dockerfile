# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /build

# Pre-fetch Maven dependencies for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy project source code and build executable JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create unprivileged non-root user and group
RUN addgroup -S spring && adduser -S spring -G spring

# Copy packaged JAR from builder stage
COPY --from=builder /build/target/krg-constructions-0.0.1-SNAPSHOT.jar app.jar

# Set ownership to spring user
RUN chown spring:spring app.jar

# Run as non-root user
USER spring:spring

# Expose default application port
EXPOSE 8080

# Configure container-aware JVM options
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"

# Launch Spring Boot application respecting Render's dynamic PORT
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar app.jar"]

