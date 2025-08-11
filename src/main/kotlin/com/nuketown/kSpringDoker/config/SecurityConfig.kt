package com.nuketown.kSpringDoker.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): org.springframework.security.crypto.password.PasswordEncoder =
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()

    @Bean
    fun jwtEncoder(secret: String): org.springframework.security.oauth2.jwt.JwtEncoder {
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val jwk = com.nimbusds.jose.jwk.OctetSequenceKey.Builder(secretKey).build()
        val jwkSource = com.nimbusds.jose.jwk.source.ImmutableJWKSet<com.nimbusds.jose.proc.SecurityContext>(
            com.nimbusds.jose.jwk.JWKSet(jwk)
        )
        return org.springframework.security.oauth2.jwt.NimbusJwtEncoder(jwkSource)
    }

    @Bean
    fun jwtDecoder(secret: String): org.springframework.security.oauth2.jwt.JwtDecoder {
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        return org.springframework.security.oauth2.jwt.NimbusJwtDecoder.withSecretKey(secretKey).build()
    }

    @Bean
    fun jwtSecret(@Value("\${jwt.secret:change-me-secret}") secret: String) = secret

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/graphql").authenticated()
                auth.requestMatchers("/api/auth/**", "/login/**", "/VAADIN/**", "/frontend/**", "/webjars/**", "/favicon.ico", "/test/**").permitAll()
            }
            .csrf { it.disable() }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .build()
    }
}