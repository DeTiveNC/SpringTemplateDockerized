package com.nuketown.kSpringDoker.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "notes")
class Notes(
    @Id
    var email: String = "",
    var description: String = "",
    val createdAt: Date = Date()
)