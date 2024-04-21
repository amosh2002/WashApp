package com.example.washapp.carwashes

data class Carwash(
    val name: String,
    val address: String,
    val distance: Float? = null,
    val rating: Float? = null,
    val ratingCount: Int = 0,
    val isFavorite: Boolean = false,
    val workHours: String = "",
    val freeHours: List<String> = emptyList(),
    val imageUrl: String = "",
    val services: List<String> = emptyList(),
) {
}