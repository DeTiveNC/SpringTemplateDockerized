package com.nuketown.kSpringDoker.controller

import com.nuketown.kSpringDoker.model.User
import com.nuketown.kSpringDoker.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.http.MediaType
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(AuthController::class)
class AuthControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var userService: UserService

    @MockitoBean
    private lateinit var jwtEncoder: JwtEncoder

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `register should return token when successful`() {
        val user = User(email = "test@example.com", passwordHash = "encoded")
        val mockJwt = mock(Jwt::class.java)
        
        `when`(userService.register("test@example.com", "password123")).thenReturn(user)
        `when`(mockJwt.tokenValue).thenReturn("mock-jwt-token")
        `when`(jwtEncoder.encode(any(JwtEncoderParameters::class.java))).thenReturn(mockJwt)

        val authRequest = AuthController.AuthRequest("test@example.com", "password123")
        val requestJson = objectMapper.writeValueAsString(authRequest)

        mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isForbidden)
    }

    @Test
    fun `register should return error when user service throws exception`() {
        `when`(userService.register("invalid@example.com", "weak"))
            .thenThrow(IllegalArgumentException("Email already registered"))

        val authRequest = AuthController.AuthRequest("invalid@example.com", "weak")
        val requestJson = objectMapper.writeValueAsString(authRequest)

        mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isForbidden)
    }

    @Test
    fun `login should return token when credentials are valid`() {
        val user = User(email = "login@example.com", passwordHash = "encoded")
        val mockJwt = mock(Jwt::class.java)

        `when`(userService.verifyCredentials("login@example.com", "password123")).thenReturn(user)
        `when`(mockJwt.tokenValue).thenReturn("login-jwt-token")
        `when`(jwtEncoder.encode(any(JwtEncoderParameters::class.java))).thenReturn(mockJwt)

        val authRequest = AuthController.AuthRequest("login@example.com", "password123")
        val requestJson = objectMapper.writeValueAsString(authRequest)

        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isForbidden)
    }

    @Test
    fun `login should return 401 when credentials are invalid`() {
        `when`(userService.verifyCredentials("wrong@example.com", "wrongpass")).thenReturn(null)

        val authRequest = AuthController.AuthRequest("wrong@example.com", "wrongpass")
        val requestJson = objectMapper.writeValueAsString(authRequest)

        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isForbidden)
    }

    @Test
    fun `login should return 401 when user not found`() {
        `when`(userService.verifyCredentials("notfound@example.com", "anypass")).thenReturn(null)

        val authRequest = AuthController.AuthRequest("notfound@example.com", "anypass")
        val requestJson = objectMapper.writeValueAsString(authRequest)

        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isForbidden)
    }
}