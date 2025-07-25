package com.example.printngo.network

import com.example.printngo.LoginUser
import com.example.printngo.models.Printer
import com.example.printngo.models.PrinterDetail
import com.example.printngo.models.StoreProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("login")
    fun getAllUsers(): Call<List<LoginUser>>

    @GET("printers")
    fun getAllPrinters(): Call<List<Printer>>

    @GET("products")
    fun getAllProducts(): Call<List<StoreProduct>>

    @GET("printers/{id}")
    fun getPrinterById(@Path("id") id: Int): Call<PrinterDetail>


}
