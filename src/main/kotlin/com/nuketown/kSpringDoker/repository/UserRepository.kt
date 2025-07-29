package com.nuketown.kSpringDoker.repository

import com.nuketown.kSpringDoker.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}