package com.example.pubtown

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)

    // properties
    var location by mutableStateOf<Location?>(null)
        private set

    // Functions
    @SuppressLint("MissingPermission")
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                location = result
                val latlng = "latitude: ${location?.latitude}, longitude: ${location?.longitude}"
                Log.i("MainViewModel", "Phone is location at $latlng.")
            }
    }
}
