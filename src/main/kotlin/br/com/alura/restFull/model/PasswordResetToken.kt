package br.com.alura.restFull.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class PasswordResetToken(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val token: String,
    val email: String,
)