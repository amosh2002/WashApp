package com.example.washapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform