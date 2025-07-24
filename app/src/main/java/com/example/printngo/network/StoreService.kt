package com.example.printngo.network

import com.example.printngo.models.StoreProduct
import retrofit2.Call
import retrofit2.http.GET

interface StoreService {
    @GET("products")
    fun getAllProducts(): Call<List<StoreProduct>>
}
