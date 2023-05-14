package com.example.righttothecity.category

import java.io.Serializable


data class Category(
    val id: String,
    val name: String,
    val image: Int,
    val subcategories: List<Category>?
) : Serializable


//Category("Улица", R.drawable.ic_street, listOf(
//Category("Транспортное средство", R.drawable.ic_transport, listOf(
//Category("Самокат", R.drawable.ic_scooter, emptyList())
//))
//))