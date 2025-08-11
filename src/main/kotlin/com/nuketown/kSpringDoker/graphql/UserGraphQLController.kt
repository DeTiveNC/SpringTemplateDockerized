package com.nuketown.kSpringDoker.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Controller

@Controller
class UserGraphQLController {

    @QueryMapping
    fun hello(): String = "Hello GraphQL!"

    @QueryMapping
    fun me(@AuthenticationPrincipal jwt: Jwt?): String? = jwt?.subject

    @MutationMapping
    fun echo(@Argument message: String): String = message
}
