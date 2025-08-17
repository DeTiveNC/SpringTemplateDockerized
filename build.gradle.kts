plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.hibernate.orm)
	alias(libs.plugins.graalvm.native)
    alias(libs.plugins.vaadin)
}

group = "com.nuketown"
version = "0.3.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

dependencies {
	// BOMs
	implementation(platform(libs.spring.ai.bom))
	implementation(platform(libs.vaadin.bom))

	// Starters
	implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.oauth2.resource.server)
	implementation(libs.spring.boot.starter.graphql)

	// Vaadin (basic UI)
	implementation(libs.vaadin.spring.boot.starter)


	// Dev tools & Kotlin
	developmentOnly(libs.spring.boot.devtools)
	implementation(libs.jackson.module.kotlin)
	implementation(libs.kotlin.reflect)

	// Databases & IA agents
	runtimeOnly(libs.postgresql)
    implementation(libs.spring.ai.starter.model.openai)

	// Test platforms
	testImplementation(libs.spring.boot.starter.test)
	testImplementation(libs.kotlin.test.junit5)
	testImplementation(libs.spring.security.test)
	testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.spring.graphql.test)
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