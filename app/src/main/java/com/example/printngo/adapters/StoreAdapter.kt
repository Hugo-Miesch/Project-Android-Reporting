package com.example.printngo.adapter

import android.content.Intent
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.ProductDetailActivity
import com.example.printngo.R
import com.example.printngo.models.StoreProduct

class StoreAdapter(
    private val products: List<StoreProduct>
) : RecyclerView.Adapter<StoreAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.productName)
        val price: TextView = view.findViewById(R.id.productPrice)
        val image: ImageView = view.findViewById(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store_product, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val p = products[position]

        holder.name.text = p.name
        holder.price.text = "${p.price} €"
        holder.image.setImageResource(p.imageResId)

        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            Intent(ctx, ProductDetailActivity::class.java).apply {
                putExtra("name", p.name)
                putExtra("desc", p.description)
                putExtra("price", p.price)
                putExtra("imageResId", p.imageResId)
            }.also { ctx.startActivity(it) }
        }
    }

    override fun getItemCount(): Int = products.size
}
