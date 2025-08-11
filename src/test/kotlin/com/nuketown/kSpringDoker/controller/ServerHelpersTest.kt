package com.nuketown.kSpringDoker.controller

import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ServerHelpersTest {

    @Test
    fun `hello should return hello message`() {
        val serverHelpers = ServerHelpers("http://issuer.com")
        assertEquals("Hello, World from Native Spring Boot!", serverHelpers.hello())
    }

    @Test
    fun `health should return status UP`() {
        val serverHelpers = ServerHelpers("http://issuer.com")
        val health = serverHelpers.health()
        assertEquals("UP", health["status"])
        assertEquals("native", health["type"])
    }

    @Test
    fun `register should redirect to account url`() {
        val issuerUri = "http://issuer.com/realms/test"
        val serverHelpers = ServerHelpers(issuerUri)
        val response = mock(HttpServletResponse::class.java)

        serverHelpers.register(response)

        verify(response).sendRedirect("$issuerUri/account")
    }
}
