//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.righttothecity.R
//
//inner class SubcategoryAdapter(private val subcategories: List<String>) :
//    RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_subcategory, parent, false)
//        return SubcategoryViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
//        val subcategory = subcategories[position]
//        holder.bind(subcategory)
//    }
//
//    override fun getItemCount() = subcategories.size
//
//    inner class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val subcategoryTextView: TextView = itemView.findViewById(R.id.subcategory_text_view)
//
//        fun bind(subcategory: String) {
//            subcategoryTextView.text = subcategory