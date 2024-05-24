# DOCKERFILE FOR SPRING BOOT APPLICATION
# This Dockerfile is used to build a Docker image for a Spring Boot application
FROM eclipse-temurin:21-alpine
EXPOSE 8080
WORKDIR /app
COPY /build/libs/dockerized.postgresql-2.5.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]