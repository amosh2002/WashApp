package com.example.washapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washapp.android.ui.components.BottomNavigationBar
import com.example.washapp.android.ui.screens.*
import com.example.washapp.carwashes.CarwashesViewModel
import com.example.washapp.navigation.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carwashesViewModel: CarwashesViewModel by viewModels()
        val navigator = DefaultNavigator()

        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "CarWashes") })
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            currentRoute = navigator.currentScreen().value.route
                        ) { route ->
                            navigator.navigateTo(NavBarItem.valueOf(route))
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        MainScreen(navigator, carwashesViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navigator: DefaultNavigator, carwashesViewModel: CarwashesViewModel) {
    val currentScreen by navigator.currentScreen.collectAsState()

    when (currentScreen) {
        NavBarItem.Home -> /* HomeScreen(navigator) */ Unit
        NavBarItem.Bonuses -> /* BonusesScreen(navigator) */ Unit
        NavBarItem.Create -> /* CreateScreen(navigator) */ Unit
        NavBarItem.List -> CarwashesListScreen(carwashesViewModel)
        NavBarItem.More -> /* MoreScreen(navigator) */ Unit
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
