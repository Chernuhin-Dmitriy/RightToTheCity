package com.example.righttothecity.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.righttothecity.R
import java.io.Serializable

class CategoryActivity : AppCompatActivity(), Serializable {

    companion object {
        const val EXTRA_CATEGORIES = "extra_categories"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter

    var categories = arrayOf(
        Category("1", "Здание/дом/сооружение", R.drawable.house, listOf(
            Category("1.1", "Пирожок 1.1", R.drawable.dog, null),
            Category("1.2", "Подкатегорияю 1.2", R.drawable.dog, null),
            Category("1.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("1.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("1.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("2", "Улица", R.drawable.ulitsa, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("3", "Общественное пространство", R.drawable.dvor, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("4", "Коммуникации", R.drawable.communications, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("5", "Животные", R.drawable.dog, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("6", "Растения/природа", R.drawable.plants, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("7", "Заведения/учреждения", R.drawable.zavedeniya, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        )),
        Category("8", "Непорядок", R.drawable.disorder, listOf(
            Category("2.1", "Подкатегория 1.1", R.drawable.dog, null),
            Category("2.2", "Подкатегория 1.2", R.drawable.dog, null),
            Category("2.3", "Подкатегория 1.3", R.drawable.dog, listOf(
                Category("2.3.1", "Подкатегория 1.3.1", R.drawable.dog, null),
                Category("2.3.2", "Подкатегория 1.3.2", R.drawable.dog, null)
            ))
        ))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        if (intent.hasExtra(EXTRA_CATEGORIES)) { //При передаче
            categories = intent.getSerializableExtra(EXTRA_CATEGORIES) as Array<Category>
            //selectedCategory = getSelectedCategory(categoryId)
        }
        //if(EXTRA_CATEGORIES != "extra_categories"){
            //Получение переданных категорий из интента
            //categories = intent.getSerializableExtra(EXTRA_CATEGORIES) as Array<Category>
        //}


        // Настройка RecyclerView
        recyclerView = findViewById(R.id.category_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 3)//LinearLayoutManager(this)
        adapter = CategoryAdapter(categories.toList())
        recyclerView.adapter = adapter
    }
}



//class CategoryActivity : AppCompatActivity() {
//    private lateinit var categoryRecyclerView: RecyclerView
//    private lateinit var categoryAdapter: CategoryAdapter
//    private val categories = listOf("Категория 1", "Категория 2", "Категория 3", "Категория 4")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_category)
//
//        categoryRecyclerView = findViewById(R.id.category_recycler_view)
//        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
//        categoryAdapter = CategoryAdapter(categories)
//        categoryRecyclerView.adapter = categoryAdapter
//    }
//
//    inner class CategoryAdapter(private val categories: List<String>) :
//        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
//            val itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_category, parent, false)
//            return CategoryViewHolder(itemView)
//        }
//
//        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
//            val category = categories[position]
//            holder.bind(category)
//        }
//
//        override fun getItemCount() = categories.size
//
//        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//            View.OnClickListener {
//            private val categoryImageView: ImageView =
//                itemView.findViewById(R.id.category_image_view)
//            private val categoryTextView: TextView = itemView.findViewById(R.id.category_text_view)
//
//            init {
//                itemView.setOnClickListener(this)
//            }
//
//            fun bind(category: String) {
//                // TODO: добавить логику для отображения соответствующей пиктограммы
//                categoryTextView.text = category
//            }
//
//            override fun onClick(view: View) {
//                // Если нажали на ImageView, переходим к следующему экрану
//                if (view.id == R.id.category_image_view) {
//                    val category = categories[adapterPosition]
//                    val intent = Intent(itemView.context, SubcategoryFragment::class.java) //SubcategoryActivity
//                    intent.putExtra("category", category)
//                    itemView.context.startActivity(intent)
//                }
//            }
//        }
//    }
//
//    companion object {
//        const val EXTRA_CATEGORIES = "com.example.righttothecity.EXTRA_CATEGORIES"
//
//    }
//}
