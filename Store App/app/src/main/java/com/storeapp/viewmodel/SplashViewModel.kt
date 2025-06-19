package com.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    fun startSplashDelay() {
        viewModelScope.launch {
            delay(2000) 
            _navigateToMain.postValue(true)
        }
    }
}
