package com.storeapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.storeapp.R
import com.storeapp.adapter.ProductAdapter
import com.storeapp.databinding.ProductListActivityBinding
import com.storeapp.utils.ProgressUtils
import com.storeapp.utils.showErrorDialog
import com.storeapp.viewmodel.ProductViewModel

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ProductListActivityBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val progressUtils = ProgressUtils()
        adapter = ProductAdapter { product ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.checkNetwork(this)
        viewModel.noInternet.observe(this) { isOffline ->
            if (isOffline) showNoInternetDialog()
        }
        viewModel.products.observe(this) {
            adapter.setData(it)
        }
        viewModel.products.observe(this) { products ->
            adapter.setData(products)
        }
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                progressUtils.showProgress(this).show()
            } else {
                progressUtils.dismissProgress()
            }
        }
        viewModel.error.observe(this) { errorMessage ->
            if (!isFinishing && !isDestroyed) {
                showErrorDialog("Data Fetch Failed", errorMessage)
            }
        }

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return true
            }
        })
        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.searchView.isIconified = false
            }
        }
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
            binding.searchView.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.searchView.findFocus(), InputMethodManager.SHOW_IMPLICIT)
        }
        viewModel.products.observe(this) { productList ->
            if (productList.isEmpty()) {
                binding.tvProductList.text = getString(R.string.data_not_found)
            } else {
                binding.tvProductList.text = getString(R.string.lbl_all_product_list)
            }
        }
        binding.rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                val lm = rv.layoutManager as LinearLayoutManager
                if (lm.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                    viewModel.loadMore()
                }
            }
        })
        viewModel.fetchProducts()
    }

    private fun showNoInternetDialog() {
        showErrorDialog(
            title = "No Internet Connection",
            message = "Please check your internet connection and open again."
        ) {
            finishAffinity()
        }
    }
}