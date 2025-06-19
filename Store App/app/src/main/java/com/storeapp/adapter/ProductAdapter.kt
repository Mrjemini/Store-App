package com.storeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.storeapp.databinding.ItemProductBinding
import com.storeapp.model.Product

class ProductAdapter(
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val productList = mutableListOf<Product>()

    fun setData(data: List<Product>) {
        productList.clear()
        productList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvTitle.text = product.title
            binding.tvPrice.text = buildString { append("$")
                append(product.price)
            }
            binding.ivImage.load(product.image)
            binding.tvDescription.text = product.description
            binding.root.setOnClickListener { onClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}