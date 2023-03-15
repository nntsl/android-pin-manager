package com.nntsl.pinmanager.feature.createpin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nntsl.pinmanager.feature.createpin.CreatePinCodeRoute

const val createPinRoute = "create_pin_route"

fun NavController.navigateToCreatePinCode() {
    this.navigate(createPinRoute)
}

fun NavGraphBuilder.createPinScreen(
    onBackClick: () -> Unit
) {
    composable(route = createPinRoute) {
        CreatePinCodeRoute(
            onBackClick = onBackClick
        )
    }
}
