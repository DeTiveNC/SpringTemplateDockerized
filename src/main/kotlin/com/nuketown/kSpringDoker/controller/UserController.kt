package com.nuketown.kSpringDoker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/users")
class UserController {
    @GetMapping("/")
    fun hello(): String {
        return "Hello, World from Native Spring Boot!"
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("status" to "UP", "type" to "native")
    }
}