package com.example.pubtown.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pubtown.MainViewModel

@Composable
fun MapScreen(viewModel: MainViewModel) {
    val location = viewModel.location

    location?.let {
        Text(
            "Lat: ${it.latitude}\nLng: ${it.longitude}"
        )
    }
}
