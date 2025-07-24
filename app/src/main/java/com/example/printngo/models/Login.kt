package com.example.printngo.models

data class LoginRequest(
    val email: String,
    val password: String
)

data class User(
    val id: Int,
    val email: String
)

data class LoginResponse(
    val token: String,
    val user: User
)