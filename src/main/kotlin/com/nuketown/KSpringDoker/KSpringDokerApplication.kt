package com.nuketown.KSpringDoker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KSpringDokerApplication

fun main(args: Array<String>) {
	runApplication<KSpringDokerApplication>(*args)
}


@RestController
class SimpleRest {
	@GetMapping("/")
	fun hello(): String {
		return "Hello, World from Native Spring Boot!"
	}

	@GetMapping("/health")
	fun health(): Map<String, String> {
		return mapOf("status" to "UP", "type" to "native")
	}
}

@Configuration
@EnableWebSecurity
class SecurityConfig {

	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		return http
			.authorizeHttpRequests { auth ->
				auth.anyRequest().permitAll()  // Permitir TODO temporalmente
			}
			.csrf { it.disable() }
			.formLogin { it.disable() }
			.httpBasic { it.disable() }
			.sessionManagement { it.disable() }
			.build()
	}
}