package com.nuketown.kSpringDoker.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.ai.openai.OpenAiChatModel

@ExtendWith(MockitoExtension::class)
class ChatGPTServiceTest {

    @Mock
    private lateinit var chatModel: OpenAiChatModel

    @InjectMocks
    private lateinit var chatGPTService: ChatGPTService

    @Test
    fun `chat should return response from chatModel`() {
        // Given
        val question = "What is the meaning of life?"
        val expectedResponse = "42"
        `when`(chatModel.call(question)).thenReturn(expectedResponse)

        // When
        val actualResponse = chatGPTService.chat(question)

        // Then
        assertEquals(expectedResponse, actualResponse)
    }
}
