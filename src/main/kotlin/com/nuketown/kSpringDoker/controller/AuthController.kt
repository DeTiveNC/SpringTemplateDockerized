package com.nuketown.kSpringDoker.controller

import com.nuketown.kSpringDoker.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.temporal.ChronoUnit

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val jwtEncoder: JwtEncoder
) {
    data class AuthRequest(val email: String = "", val password: String = "")
    data class AuthResponse(val token: String)

    @PostMapping("/register")
    fun register(@RequestBody req: AuthRequest): ResponseEntity<AuthResponse> {
        val user = userService.register(req.email, req.password)
        val token = generateToken(user.email)
        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): ResponseEntity<AuthResponse> {
        val user = userService.verifyCredentials(req.email, req.password)
            ?: return ResponseEntity.status(401).build()
        val token = generateToken(user.email)
        return ResponseEntity.ok(AuthResponse(token))
    }

    private fun generateToken(subjectEmail: String): String {
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(subjectEmail)
            .claim("scope", "USER")
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}
