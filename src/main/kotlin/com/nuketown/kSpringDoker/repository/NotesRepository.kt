package com.nuketown.kSpringDoker.repository

import com.nuketown.kSpringDoker.model.Notes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface NotesRepository: JpaRepository<Notes, String> {
    fun findNotesByEmail(email: String): List<Notes>
    fun findNotesByCreatedAtAfter(createdAt: Date): List<Notes>
}