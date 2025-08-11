package com.nuketown.kSpringDoker.service

import com.nuketown.kSpringDoker.model.Notes
import com.nuketown.kSpringDoker.repository.NotesRepository
import org.springframework.stereotype.Service
import java.util.Date

@Service
class NotesService(
    private val notesRepository: NotesRepository,
) {
    fun getNotesByEmail(email: String): List<Notes> = notesRepository.findNotesByEmail(email)
    fun findNotesByCreatedAtAfter(date: Date): List<Notes> = notesRepository.findNotesByCreatedAtAfter(date)
}