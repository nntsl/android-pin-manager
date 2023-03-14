package com.nntsl.pinmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nntsl.pinmanager.feature.pin.navigation.pinGraph
import com.nntsl.pinmanager.feature.pin.navigation.pinRoutePattern

@Composable
fun PinManagerNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = pinRoutePattern
    ) {
        pinGraph()
    }
}
