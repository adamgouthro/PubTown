package com.example.pubtown.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pubtown.MainViewModel

//please commit


import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap

import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.annotation.Marker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pubtown.R

import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage


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

        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = viewportState
        ) {
//            Marker(
//                point = point,
//                color = Color.Red,
//                //stroke changes the outline of the marker
//                stroke = Color.Black
//            )

            // Source : https://docs.mapbox.com/android/maps/guides/add-your-data/annotations/
            val marker = rememberIconImage(key = "beer-marker", painter = painterResource(id = R.drawable.beer))

            PointAnnotation(point = point) {
                iconImage = marker
                iconSize = 0.35
            }
        }
    }
}






