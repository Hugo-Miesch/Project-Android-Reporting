package com.example.printngo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrocfitClient {
    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/LucaGuilliere/android-fake-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
