package com.example.pubtown.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pubtown.MainViewModel




import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap

import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.annotation.Marker

import androidx.compose.ui.graphics.Color

import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState



@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(viewModel: MainViewModel) {

    val location = viewModel.location

    if (location != null) {

        val latitude = location.latitude
        val longitude = location.longitude

        val point = Point.fromLngLat(longitude, latitude)

        val viewportState = rememberMapViewportState {
            setCameraOptions {
                center(point)
                zoom(11.0)
            }
        }
        // just commit already

        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = viewportState
        ) {
            Marker(
                point = point,
                color = Color.Red
            )
        }
    }
}






