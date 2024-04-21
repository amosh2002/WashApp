package com.example.washapp.carwashes

data class CarwashState(
    val carwashesLoaded: List<Carwash> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)