package com.example.printngo.network

import com.example.printngo.LoginUser
import com.example.printngo.models.LoginRequest
import com.example.printngo.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("login")
    fun getAllUsers(): Call<List<LoginUser>>
}
