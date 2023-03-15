package com.nntsl.pinmanager.feature.pincodes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PinCodesRoute(
    modifier: Modifier = Modifier,
    viewModel: PinViewModel = hiltViewModel(),
    navigateToCreatePinCode: () -> Unit
) {
    val pinCodesState by viewModel.pinCodesUiState.collectAsStateWithLifecycle()

    PinCodesScreen(
        uiState = pinCodesState,
        deletePinCode = viewModel::deletePinCode,
        modifier = modifier,
        navigateToCreatePinCode = navigateToCreatePinCode
    )
}
