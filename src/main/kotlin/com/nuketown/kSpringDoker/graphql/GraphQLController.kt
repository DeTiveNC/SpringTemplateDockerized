package com.nuketown.kSpringDoker.graphql

import com.nuketown.kSpringDoker.service.ChatGPTService
import com.nuketown.kSpringDoker.service.NotesService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Controller
import java.util.Date

@Controller
class GraphQLController(
    private val notesService: NotesService,
    private val chatGPTService: ChatGPTService
) {

    @QueryMapping
    fun hello(): String = "Hello GraphQL!"

    @QueryMapping
    fun me(@AuthenticationPrincipal jwt: Jwt?): String? = jwt?.subject

    @MutationMapping
    fun echo(@Argument message: String): String = message

    @MutationMapping
    fun findNotesByEmail(@Argument email: String): List<NotesResponse> = notesService.getNotesByEmail(email).
        map { NotesResponse(it.description, it.createdAt.toString()) }

    @MutationMapping
    fun findNotesCreatedAtAfter(@Argument date: Date): List<NotesResponse> = notesService.findNotesByCreatedAtAfter(date).
        map { NotesResponse(it.description, it.createdAt.toString()) }

    @MutationMapping
    fun askChatGPT(@Argument prompt: String): AIAnswer = AIAnswer(chatGPTService.chat(prompt))

    data class NotesResponse(val description: String, val createdAt: String)
    data class AIAnswer(val text: String)
}
