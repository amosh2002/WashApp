package com.example.washapp.android.ui.screens

import android.content.Loader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.washapp.BaseViewModel
import com.example.washapp.android.R
import com.example.washapp.carwashes.Carwash
import com.example.washapp.carwashes.CarwashesViewModel

@Composable
fun CarwashesListScreen(carwashesViewModel: CarwashesViewModel) {

    val carwashesState = carwashesViewModel.carwashesState.collectAsState()

    Column {
        if (carwashesState.value.loading) {
            // Show loading indicator
            CircularProgressIndicator()
        } else if (carwashesState.value.error != null) {
            // Show error message
            Text("Error: ${carwashesState.value.error}", color = MaterialTheme.colorScheme.error)
        } else {
            // Show carwashes list
            CarwashesList(carwashesState.value.carwashesLoaded)
        }
    }
}

@Composable
private fun CarwashesList(carwashes: List<Carwash>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(carwashes) { carwash ->
            // Display carwash item
            CarwashItem(carwash)
        }
    }
}

// composable to display a carwash item
@Composable
private fun CarwashItem(carwash: Carwash) {
    Column(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        AsyncImage(
            model = carwash.imageUrl,
            contentDescription = carwash.name + " image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(15.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(15.dp))
        )
        Row(
            modifier = Modifier
                .padding(top = 3.dp, start = 3.dp, end = 3.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = carwash.name,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.weight(1f)) // This will take up any remaining space
                Text(
                    text = carwash.address,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(end = 2.dp, top = 3.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        painter = painterResource(id = R.drawable.hours),
                        contentDescription = "Hours of operation",
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = carwash.workHours,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (carwash.rating != null) {
                        Text(
                            text = carwash.rating!!.toInt().toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .padding(end = 3.dp)
                        )
                        for (i in 1..carwash.rating!!.toInt()) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating Star $i",
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                                    .padding(end = 1.dp)
                                    .size(21.dp)
                            )
                        }
                        Text(
                            text = "(${carwash.ratingCount})",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    } else {
                        Text(
                            text = "N/A",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }

                }
            }
        }
    }
}