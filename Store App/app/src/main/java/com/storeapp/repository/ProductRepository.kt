package com.storeapp.repository

import com.storeapp.network.RetrofitInstance

class ProductRepository {
    suspend fun getProducts() = RetrofitInstance.api.getProducts()
}