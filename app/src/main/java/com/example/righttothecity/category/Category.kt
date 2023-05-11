package com.example.righttothecity.category


data class Category(
    val id: String,
    val name: String,
    val image: Int,
    val subcategories: List<Category>?
)


//Category("Улица", R.drawable.ic_street, listOf(
//Category("Транспортное средство", R.drawable.ic_transport, listOf(
//Category("Самокат", R.drawable.ic_scooter, emptyList())
//))
//))