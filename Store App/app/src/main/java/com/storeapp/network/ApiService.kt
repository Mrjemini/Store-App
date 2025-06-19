package com.storeapp.network

import com.storeapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}