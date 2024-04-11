plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.pee"
version = "2.5.0"
description = "dockerized.postgresql"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    // JSON Web Token Support
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5") // For JSON processing

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-api
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.5.0")



    // Lombok for simplifying getter, setter, etc.
    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
