package com.example.washapp.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    fun navigateTo(destination: NavBarItem)
    fun goBack()
}

class DefaultNavigator : Navigator {
    private val _currentScreen = MutableStateFlow<NavBarItem>(NavBarItem.Home)
    val currentScreen: StateFlow<NavBarItem> = _currentScreen

    override fun navigateTo(destination: NavBarItem) {
        _currentScreen.value = destination
    }

    override fun goBack() {
        // Handle back navigation if necessary
    }

    fun currentScreen(): StateFlow<NavBarItem> = _currentScreen
}