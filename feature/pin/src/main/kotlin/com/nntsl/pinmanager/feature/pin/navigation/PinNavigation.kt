package com.nntsl.pinmanager.feature.pin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val pinRoute = "pin_route"
const val pinRoutePattern = "pin_graph"

fun NavGraphBuilder.pinGraph() {
    navigation(
        route = pinRoutePattern,
        startDestination = pinRoute
    ) {
        composable(route = pinRoute) {
            PinRoute()
        }
    }
}
