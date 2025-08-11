package com.nuketown.kSpringDoker.graphql

import com.nuketown.kSpringDoker.model.Notes
import com.nuketown.kSpringDoker.service.ChatGPTService
import com.nuketown.kSpringDoker.service.NotesService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.oauth2.jwt.Jwt
import java.util.Date

@ExtendWith(MockitoExtension::class)
class GraphQLControllerTest {

    @Mock
    private lateinit var notesService: NotesService

    @Mock
    private lateinit var chatGPTService: ChatGPTService

    @InjectMocks
    private lateinit var graphQLController: GraphQLController

    @Test
    fun `hello should return hello message`() {
        assertEquals("Hello GraphQL!", graphQLController.hello())
    }

    @Test
    fun `me should return jwt subject`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", "test-subject")
            .build()
        assertEquals("test-subject", graphQLController.me(jwt))
    }

    @Test
    fun `me should return null if jwt is null`() {
        assertEquals(null, graphQLController.me(null))
    }

    @Test
    fun `echo should return the same message`() {
        val message = "Hello"
        assertEquals(message, graphQLController.echo(message))
    }

    @Test
    fun `findNotesByEmail should return notes`() {
        val email = "test@example.com"
        val date = Date()
        val notes = listOf(Notes(email, "note 1", date))
        `when`(notesService.getNotesByEmail(email)).thenReturn(notes)

        val result = graphQLController.findNotesByEmail(email)

        assertEquals(1, result.size)
        assertEquals("note 1", result[0].description)
        assertEquals(date.toString(), result[0].createdAt)
    }

    @Test
    fun `findNotesCreatedAtAfter should return notes`() {
        val date = Date()
        val notes = listOf(Notes("test@example.com", "note 1", date))
        `when`(notesService.findNotesByCreatedAtAfter(date)).thenReturn(notes)

        val result = graphQLController.findNotesCreatedAtAfter(date)

        assertEquals(1, result.size)
        assertEquals("note 1", result[0].description)
        assertEquals(date.toString(), result[0].createdAt)
    }

    @Test
    fun `askChatGPT should return AI answer`() {
        val prompt = "What is the meaning of life?"
        val answer = "42"
        `when`(chatGPTService.chat(prompt)).thenReturn(answer)

        val result = graphQLController.askChatGPT(prompt)

        assertEquals(answer, result.text)
    }
}
