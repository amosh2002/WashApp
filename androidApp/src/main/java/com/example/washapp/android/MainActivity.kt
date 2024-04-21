package com.example.washapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.washapp.android.ui.components.BottomNavigationBar
import com.example.washapp.android.ui.screens.CarwashesListScreen
import com.example.washapp.carwashes.CarwashesViewModel
import com.example.washapp.navigation.NavigationItem

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a ViewModel instance (Android connects the viewModels automatically using by viewModels())
        val carwashesViewModel: CarwashesViewModel by viewModels()

        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "CarWashes") })
                    },
                    bottomBar = {
                        BottomNavigationBar(currentRoute = NavigationItem.List.route) {}
                    })
                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        CarwashesListScreen(carwashesViewModel)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
