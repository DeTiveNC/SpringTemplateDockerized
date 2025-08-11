package com.nuketown.kSpringDoker.controller

import com.nuketown.kSpringDoker.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/test")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/")
    fun hello(): String {
        return "Hello, World from Native Spring Boot!"
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("status" to "UP", "type" to "native")
    }
}