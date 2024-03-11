# Dockerfile for building a custom application image
# Version: 1.0
FROM vegardit/graalvm-maven:latest-java21
LABEL maintainer="Nicolas Cao nicolascao12@gmail.com"
COPY target/*.jar app.jar
CMD [ "java", "-jar", "app.jar" ]