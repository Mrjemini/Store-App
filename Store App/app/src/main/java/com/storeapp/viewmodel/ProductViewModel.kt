package com.storeapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storeapp.model.Product
import com.storeapp.repository.ProductRepository
import com.storeapp.utils.NetworkUtils
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _noInternet = MutableLiveData<Boolean>()
    val noInternet: LiveData<Boolean> get() = _noInternet

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    private var fullProductList = listOf<Product>()
    private var currentIndex = 0
    private val pageSize = 10

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                loading.value = true
                val response = repository.getProducts()
                fullProductList = response
                currentIndex = pageSize
                _products.value = fullProductList.take(currentIndex)
                loading.value = false
            } catch (e: Exception) {
                error.value = "Error: ${e.message}"
                loading.value = false
            }
        }
    }
    fun checkNetwork(context: Context) {
        _noInternet.value = !NetworkUtils.isInternetAvailable(context)
    }
    fun loadMore() {
        val currentList = _products.value ?: emptyList()
        val nextIndex = currentIndex + pageSize
        if (nextIndex <= fullProductList.size) {
            _products.value = currentList + fullProductList.subList(currentIndex, nextIndex)
            currentIndex = nextIndex
        }
    }

    fun search(query: String) {
        val filtered = fullProductList.filter {
            it.title.contains(query, ignoreCase = true)
        }
        _products.value = filtered.take(pageSize)
        currentIndex = pageSize
    }
}