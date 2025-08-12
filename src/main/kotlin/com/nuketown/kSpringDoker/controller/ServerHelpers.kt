package com.nuketown.kSpringDoker.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class ServerHelpers(
    @Value($$"${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuerUri: String
) {
    @GetMapping("/")
    fun hello(): String {
        return "Hello, World from Native Spring Boot!"
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("status" to "UP", "type" to "native")
    }

    // Redirige /register -> {issuer-uri}/account
    @GetMapping("/register")
    fun register(response: HttpServletResponse) {
        val target = buildAccountUrl()
        response.sendRedirect(target)
    }

    private fun buildAccountUrl(): String {
        val base = issuerUri.trimEnd('/')
        // Para Keycloak típico: http://host/realms/{realm}/account
        return "$base/account"
    }
}