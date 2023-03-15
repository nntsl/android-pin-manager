package com.nntsl.pinmanager.feature.pincodes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nntsl.pinmanager.feature.pincodes.PinCodesRoute

const val pinRoute = "pin_route"
const val pinRoutePattern = "pin_graph"

fun NavGraphBuilder.pinGraph(
    navigateToCreatePinCode: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = pinRoutePattern,
        startDestination = pinRoute
    ) {
        composable(route = pinRoute) {
            PinCodesRoute(navigateToCreatePinCode = navigateToCreatePinCode)
        }
        nestedGraphs()
    }
}
