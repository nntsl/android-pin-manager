package com.nntsl.pinmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nntsl.pinmanager.feature.createpin.navigation.createPinScreen
import com.nntsl.pinmanager.feature.createpin.navigation.navigateToCreatePinCode
import com.nntsl.pinmanager.feature.pincodes.navigation.pinGraph
import com.nntsl.pinmanager.feature.pincodes.navigation.pinRoutePattern

@Composable
fun PinManagerNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = pinRoutePattern
    ) {
        pinGraph(
            navigateToCreatePinCode = {
                navController.navigateToCreatePinCode()
            },
            nestedGraphs = {
                createPinScreen(onBackClick)
            }
        )
    }
}
