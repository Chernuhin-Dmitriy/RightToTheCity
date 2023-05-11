package com.example.righttothecity.category

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.righttothecity.R

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.category_image_view)
        private val titleTextView: TextView = itemView.findViewById(R.id.category_text_view)

        fun bind(category: Category) {
            iconImageView.setImageResource(category.image)
            titleTextView.text = category.name
            itemView.setOnClickListener {
                val subcategories = category.subcategories
                if (subcategories!!.isNotEmpty()) {
                    val intent = Intent(itemView.context, CategoryActivity::class.java)
                    intent.putExtra(CategoryActivity.EXTRA_CATEGORIES, subcategories.toTypedArray())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
