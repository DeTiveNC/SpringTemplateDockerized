package com.nuketown.kSpringDoker.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: java.util.UUID? = null,
    var email: String = "",
    var passwordHash: String = ""
)