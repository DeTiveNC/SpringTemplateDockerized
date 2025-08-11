package com.nuketown.kSpringDoker.frontend

import com.vaadin.flow.component.notification.Notification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Field
import java.lang.reflect.Method

@ExtendWith(MockitoExtension::class)
class LoginTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    private lateinit var login: Login

    @BeforeEach
    fun setUp() {
        login = Login("http://auth.com/token")
        val restTemplateField: Field = Login::class.java.getDeclaredField("restTemplate")
        restTemplateField.isAccessible = true
        restTemplateField.set(login, restTemplate)
    }

    @Test
    fun `authCall should show token on success`() {
        Mockito.mockStatic(Notification::class.java).use { mockedNotification ->
            // Given
            val responseBody = mapOf("access_token" to "test_token")
            val responseEntity = ResponseEntity<Map<*, *>>(responseBody, HttpStatus.OK)
            Mockito.`when`(restTemplate.postForEntity(any(String::class.java), any(HttpEntity::class.java), eq(Map::class.java))).thenReturn(responseEntity)

            // When
            val authCallMethod: Method = Login::class.java.getDeclaredMethod("authCall")
            authCallMethod.isAccessible = true
            authCallMethod.invoke(login)

            // Then
            mockedNotification.verify { Notification.show("JWT: test_token") }
        }
    }

    @Test
    fun `authCall should show error on exception`() {
        Mockito.mockStatic(Notification::class.java).use { mockedNotification ->
            // Given
            Mockito.`when`(restTemplate.postForEntity(any(String::class.java), any(HttpEntity::class.java), eq(Map::class.java))).thenThrow(RuntimeException("Connection failed"))

            // When
            val authCallMethod: Method = Login::class.java.getDeclaredMethod("authCall")
            authCallMethod.isAccessible = true
            authCallMethod.invoke(login)

            // Then
            mockedNotification.verify { Notification.show(any(String::class.java)) }
        }
    }
}
