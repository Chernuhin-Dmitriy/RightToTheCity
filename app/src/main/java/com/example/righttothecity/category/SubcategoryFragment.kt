//package com.example.righttothecity.category
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.righttothecity.R
//
//class SubcategoryFragment : Fragment() {
//    private lateinit var subcategoryRecyclerView: RecyclerView
//    private lateinit var subcategoryAdapter: SubcategoryAdapter
//    private var subcategories: List<String> = listOf()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_subcategory, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val category = arguments?.getString("category")
//        subcategories = getSubcategoriesForCategory(category)
//
//        subcategoryRecyclerView = view.findViewById(R.id.subcategory_recycler_view)
//        subcategoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        subcategoryAdapter = SubcategoryAdapter(subcategories)
//        subcategoryRecyclerView.adapter = subcategoryAdapter
//    }
//
//    private fun getSubcategoriesForCategory(category: String?): List<String> {
//        if (category == null) {
//            return emptyList()
//        }
//
//        val subcategoriesArrayResourceId = when (category) {
//            "Здание/дом/сооружение" -> R.drawable.house
//            "Улица" -> R.drawable.ulitsa
//            "Двор/площадь/парк/общественное пространство" -> R.drawable.dvor
//            "Коммуникации" -> R.array.subcategories_4
//            "Животные" -> R.array.subcategories_5
//            "Растения/природа" -> R.array.subcategories_6
//            "Непорядок" -> R.array.subcategories_7
//            else -> 0
//        }
//
//        if (subcategoriesArrayResourceId == 0) {
//            return emptyList()
//        }
//
//        return resources.getStringArray(subcategoriesArrayResourceId).toList()
//    }
//}
//
