package com.example.washapp.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.washapp.navigation.NavigationItem

@Composable
fun BottomNavigationBar(currentRoute: String, onItemSelected: (String) -> Unit) {

    val items = NavigationItem.entries.toTypedArray()
    var selectedItem by remember { mutableStateOf(currentRoute) }

    // Custom Bottom Navigation Bar
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = MaterialTheme.colorScheme.surface)
//            .padding(vertical = 8.dp), // Adjust padding as needed
//        horizontalArrangement = Arrangement.SpaceBetween, // Distributes the items evenly
//
//    ) {
//        items.forEach { item ->
//            Box(
//                modifier = Modifier
//                    .weight(1f) // This will distribute the space evenly across items
//                    .align(Alignment.CenterVertically) // This will center the items vertically within the Row
//                    .clickable { // This will make the Box clickable
//                        selectedItem = item.route
//                        onItemSelected(item.route)},
//                contentAlignment = Alignment.Center // This centers the icon and text within the Box
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally, // Centers the Column items horizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Home, // Assumes NavigationItem has an icon property
//                        contentDescription = item.name,
//                        modifier = Modifier.size(24.dp),
//                        tint = if (selectedItem == item.route) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary // Tint color changes based on selection
//                    )
//                    Text(
//                        item.name,
//                        fontSize = 12.sp,
//                        color = if (selectedItem == item.route) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary // Text color changes based on selection
//                    )
//                }
//            }
//        }
//    }


    // Material Bottom Navigation Bar
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier,
                icon = { Icon(imageVector = when (item) {
                    NavigationItem.Home -> Icons.Filled.Home
                    NavigationItem.Bonuses -> Icons.Filled.Star
                    NavigationItem.Create -> Icons.Filled.AddCircle
                    NavigationItem.List -> Icons.Filled.Notifications
                    NavigationItem.More -> Icons.Filled.Menu
                }, contentDescription = item.name) },
                label = { Text(item.name, fontSize = 12.sp) },
                selected = selectedItem == item.route,
                onClick = {
                    selectedItem = item.route
                    onItemSelected(item.route)
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent
                ),
            )
        }
    }
}
