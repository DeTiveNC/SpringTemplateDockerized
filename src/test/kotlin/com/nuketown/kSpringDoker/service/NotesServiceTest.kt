package com.nuketown.kSpringDoker.service

import com.nuketown.kSpringDoker.model.Notes
import com.nuketown.kSpringDoker.repository.NotesRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Date

@ExtendWith(MockitoExtension::class)
class NotesServiceTest {

    @Mock
    private lateinit var notesRepository: NotesRepository

    @InjectMocks
    private lateinit var notesService: NotesService

    @Test
    fun `getNotesByEmail should return notes from repository`() {
        // Given
        val email = "test@example.com"
        val expectedNotes = listOf(Notes(email = email, description = "Test note", createdAt = Date()))
        `when`(notesRepository.findNotesByEmail(email)).thenReturn(expectedNotes)

        // When
        val actualNotes = notesService.getNotesByEmail(email)

        // Then
        assertEquals(expectedNotes, actualNotes)
    }

    @Test
    fun `findNotesByCreatedAtAfter should return notes from repository`() {
        // Given
        val date = Date()
        val expectedNotes = listOf(Notes(email = "test@example.com", description = "Test note", createdAt = date))
        `when`(notesRepository.findNotesByCreatedAtAfter(date)).thenReturn(expectedNotes)

        // When
        val actualNotes = notesService.findNotesByCreatedAtAfter(date)

        // Then
        assertEquals(expectedNotes, actualNotes)
    }
}
