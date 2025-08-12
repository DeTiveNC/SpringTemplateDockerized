plugins {
	kotlin("jvm") version "2.2.0"
	kotlin("plugin.spring") version "2.2.0"
    kotlin("plugin.jpa") version "2.2.0"

	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.hibernate.orm") version "7.0.9.Final"
	id("org.graalvm.buildtools.native") version "0.11.0"
    id("com.vaadin") version "24.8.6"
}

group = "com.nuketown"
version = "0.3.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springAiVersion"] = "1.0.1"
extra["vaadinVersion"] = "24.8.6"

dependencies {
	// Starters
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-graphql")

	// Vaadin (basic UI)
	implementation("com.vaadin:vaadin-spring-boot-starter")


	// Dev tools & Kotlin
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Databases & IA agents
	runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.ai:spring-ai-starter-model-openai")

	// Test platforms
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.graphql:spring-graphql-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
        mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
    }
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

hibernate {
	enhancement {
		enableAssociationManagement = true
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

graalvmNative {
	binaries {
		named("main") {
			imageName.set("spring-app")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}