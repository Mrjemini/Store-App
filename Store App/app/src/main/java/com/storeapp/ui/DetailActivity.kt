package com.storeapp.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.storeapp.databinding.ActivityDetailBinding
import com.storeapp.model.Product
import com.storeapp.utils.applySystemWindowInsets


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lTopParent.applySystemWindowInsets()
        this.supportActionBar?.title = "Product Info"
        val product: Product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("product", Product::class.java)
        } else {
            @Suppress("DEPRECATION") intent.getParcelableExtra("product") as? Product
        } ?: run {
            finish()
            return
        }
        binding.tvProductTitle.text = product.title
        binding.tvProductCategory.text = product.category
        binding.tvProductDescription.text = product.description
        binding.tvProductPrice.text = buildString {
            append("$")
            append(product.price)
        }
        binding.tvProductRating.text = buildString {
            append("Rating: ")
            append(product.rating.rate)
            append(" (")
            append(product.rating.count)
            append(" reviews)")
        }
        binding.ivProductImage.load(product.image)
    }

}