package com.nuketown.kSpringDoker.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value($$"${spring.security.oauth2.resourceserver.jwt.issuer-uri}") val jwtIssuerUri: String
) {

    @Bean
    fun jwtDecoder(): JwtDecoder =
        JwtDecoders.fromIssuerLocation(jwtIssuerUri)

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { it
                .requestMatchers("/graphql").authenticated()
                .requestMatchers("/graphiql", "/health", "/register", "/login/**", "/VAADIN/**", "/frontend/**", "/webjars/**", "/favicon.ico",).permitAll()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .build()
    }
}