# Build stage
FROM ghcr.io/graalvm/graalvm-community:21 AS build
WORKDIR /workspace/app

# Copy build files
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle

# Download dependencies (separate cache layer for optimization)
RUN gradle dependencies --no-daemon

# Copy source code
COPY src src

# Build the application
RUN gradle build --no-daemon -x test

# Production stage
FROM alpine:latest
WORKDIR /app

# Instalar dependencias mínimas para el ejecutable nativo
RUN apk add --no-cache libstdc++

# Environment variables for configuration
ENV JWT_SECRET=defaultSecretChangeMeInProduction
ENV JWT_EXPIRATION=86400000

# Create non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el ejecutable nativo
COPY --from=builder /workspace/app/build/native/nativeCompile/app /app/spring-app

# Expose application port
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["/app/spring-app"]