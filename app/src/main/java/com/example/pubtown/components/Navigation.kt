package com.example.pubtown.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pubtown.MainViewModel
import com.example.pubtown.ui.screens.MapScreen
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.graphics.Color



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    // Navigation
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) } // default set to 0

    // Initialize view model here and pass into screens
    val viewModel: MainViewModel = viewModel()
    // Begin: Ask permission to get device location.
    // Note: If permission is previously granted, the dialog won't appear and refreshLocation() will get called.
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            viewModel.refreshLocation() // call a function to get the current location for device
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
    // End: Ask permission to get device location.

    // Main display (top bar, screens, bottom nav bar)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFFB18A56),
                    titleContentColor = Color(0xFF000000),
                ),
                title = {
                    Text("Pub Town")
                }
            )
        },
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = Color(0xFFB18A56))
            {
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "MapScreen") },
                    label = { Text("MapScreen") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(route = "MapScreen")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFD85045),
                        selectedTextColor = Color(0xFF000000),
                        indicatorColor = Color(0xFFA5C271),
                        unselectedIconColor = Color(0xFFD85045),
                        unselectedTextColor = Color(0xFF000000),
                    )
                )
            }
        }
    )
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "MapScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "MapScreen") {
                MapScreen(viewModel)
            }
        }
    }
}