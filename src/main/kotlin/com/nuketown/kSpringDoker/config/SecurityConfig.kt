package com.nuketown.kSpringDoker.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun jwtEncoder(secret: String): JwtEncoder {
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val jwk = OctetSequenceKey.Builder(secretKey).build()
        val jwkSource = ImmutableJWKSet<SecurityContext>(
            JWKSet(jwk)
        )
        return NimbusJwtEncoder(jwkSource)
    }

    @Bean
    fun jwtDecoder(secret: String): JwtDecoder {
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        return NimbusJwtDecoder.withSecretKey(secretKey).build()
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