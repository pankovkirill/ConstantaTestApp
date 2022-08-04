package com.example.constantatestapp.model.data

data class DataModel(
    val items: List<Items>
) {
    fun orderItems() = DataModel(items.sortedBy { it.releaseYear })
}