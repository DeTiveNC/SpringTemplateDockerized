package com.nuketown.kSpringDoker.service

import com.nuketown.kSpringDoker.model.User
import com.nuketown.kSpringDoker.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(email: String, rawPassword: String): User {
        require(email.isNotBlank()) { "Email is required" }
        require(rawPassword.isNotBlank()) { "Password is required" }
        userRepository.findByEmail(email)?.let { throw IllegalArgumentException("Email already registered") }
        val user = User(
            email = email,
            passwordHash = passwordEncoder.encode(rawPassword)
        )
        return userRepository.save(user)
    }

    fun verifyCredentials(email: String, rawPassword: String): User? {
        val user = userRepository.findByEmail(email) ?: return null
        return if (passwordEncoder.matches(rawPassword, user.passwordHash)) user else null
    }
}