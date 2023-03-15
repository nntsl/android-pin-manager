package com.nntsl.pinmanager.feature.createpin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CreatePinCodeRoute(
    modifier: Modifier = Modifier,
    viewModel: CreatePinCodeViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val pinCodeState by viewModel.createPinCodeUiState.collectAsStateWithLifecycle()

    CreatePinCodeScreen(
        onBackClick = onBackClick,
        modifier = modifier,
        uiState = pinCodeState,
        updatePinName = viewModel::updatePinName,
        createPinCode = viewModel::createPinCode
    )
}
