package com.nuketown.kSpringDoker.service

import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Service

@Service
class ChatGPTService(
    private val chatModel: OpenAiChatModel
) {
    fun chat(question: String): String = chatModel.call(question)
}