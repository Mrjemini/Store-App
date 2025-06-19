package com.storeapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.storeapp.R
import com.storeapp.viewmodel.SplashViewModel


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel.navigateToMain.observe(this) { shouldNavigate ->
            println("shouldNavigate = $shouldNavigate")
            if (shouldNavigate) {
                startActivity(Intent(this, ProductListActivity::class.java))
                finish()
            }
        }
        viewModel.startSplashDelay()
    }
}