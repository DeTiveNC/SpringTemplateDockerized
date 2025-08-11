package com.nuketown.kSpringDoker.service

import com.nuketown.kSpringDoker.model.User
import com.nuketown.kSpringDoker.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest {

    @Test
    fun `register should save new user with encoded password`() {
        val repo = mock(UserRepository::class.java)
        val encoder = mock(PasswordEncoder::class.java)
        val service = UserService(repo, encoder)

        `when`(repo.findByEmail("test@example.com")).thenReturn(null)
        `when`(encoder.encode("secret")).thenReturn("ENC:secret")
        // save returns the same entity back
        `when`(repo.save(any(User::class.java))).thenAnswer { it.arguments[0] as User }

        val user = service.register("test@example.com", "secret")

        assertEquals("test@example.com", user.email)
        assertEquals("ENC:secret", user.passwordHash)

        val captor = ArgumentCaptor.forClass(User::class.java)
        verify(repo).save(captor.capture())
        val saved = captor.value
        assertEquals("test@example.com", saved.email)
        assertEquals("ENC:secret", saved.passwordHash)
    }

    @Test
    fun `register should fail when email is blank or password is blank`() {
        val service = UserService(mock(UserRepository::class.java), mock(PasswordEncoder::class.java))

        val ex1 = assertThrows(IllegalArgumentException::class.java) {
            service.register("", "pw")
        }
        assertTrue(ex1.message!!.contains("Email is required"))

        val ex2 = assertThrows(IllegalArgumentException::class.java) {
            service.register("a@b.c", "")
        }
        assertTrue(ex2.message!!.contains("Password is required"))
    }

    @Test
    fun `register should fail when email already exists`() {
        val repo = mock(UserRepository::class.java)
        val encoder = mock(PasswordEncoder::class.java)
        val service = UserService(repo, encoder)

        `when`(repo.findByEmail("dup@example.com")).thenReturn(User(email = "dup@example.com", passwordHash = "x"))

        val ex = assertThrows(IllegalArgumentException::class.java) {
            service.register("dup@example.com", "pw2")
        }
        assertTrue(ex.message!!.contains("Email already registered"))
        verify(repo, never()).save(any(User::class.java))
    }

    @Test
    fun `verifyCredentials should return user on valid password and null otherwise`() {
        val repo = mock(UserRepository::class.java)
        val encoder = mock(PasswordEncoder::class.java)
        val service = UserService(repo, encoder)

        val existing = User(email = "login@example.com", passwordHash = "ENC:pass")
        `when`(repo.findByEmail("login@example.com")).thenReturn(existing)

        `when`(encoder.matches("pass", "ENC:pass")).thenReturn(true)
        `when`(encoder.matches("wrong", "ENC:pass")).thenReturn(false)

        val ok = service.verifyCredentials("login@example.com", "pass")
        assertNotNull(ok)
        assertEquals(existing.email, ok!!.email)

        val wrong = service.verifyCredentials("login@example.com", "wrong")
        assertNull(wrong)

        val missing = service.verifyCredentials("missing@example.com", "any")
        assertNull(missing)
    }
}
