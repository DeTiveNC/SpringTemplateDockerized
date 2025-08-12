package com.nuketown.kSpringDoker.frontend

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@Route("login")
class Login(
    @Value($$"${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val path: String
) : VerticalLayout() {
    private val email = EmailField("Email")
    private val password = PasswordField("Password")
    private val loginBtn = Button("Login")
    private val restTemplate = RestTemplate()

    init {
        val form = FormLayout()
        form.add(email, password)
        add(form, loginBtn)

        loginBtn.addClickListener {
            authCall()
        }
    }

    private fun authCall() {
        try {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            val reqBody = mapOf("email" to email.value, "password" to password.value)
            val entity = HttpEntity(reqBody, headers)
            val response = restTemplate.postForEntity(path, entity, Map::class.java)
            val token = response.body?.get("access_token") as? String
            if (token != null) {
                Notification.show("JWT: $token")
            } else {
                Notification.show("No token returned")
            }
        } catch (e: Exception) {
            Notification.show($$"Error: ${e.message}")
        }
    }
}