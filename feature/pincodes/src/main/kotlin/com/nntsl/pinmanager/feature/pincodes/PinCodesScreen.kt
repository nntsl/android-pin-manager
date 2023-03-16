package com.nntsl.pinmanager.feature.pincodes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nntsl.pinmanager.core.model.PinCode
import com.nntsl.pinmanager.feature.pincodes.R.string

@Composable
internal fun PinCodesScreen(
    uiState: PinCodesUiState,
    modifier: Modifier = Modifier,
    deletePinCode: (Int) -> Unit,
    navigateToCreatePinCode: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (uiState) {
            PinCodesUiState.Loading -> LoadingState(modifier)
            is PinCodesUiState.Success -> if (uiState.pinCodes.isNotEmpty()) {
                PinCodesContent(
                    modifier = modifier,
                    pinCodes = uiState.pinCodes,
                    deletePinCode = deletePinCode
                )
            } else {
                EmptyState()
            }
            PinCodesUiState.Error -> EmptyState()
        }
        FloatingActionButton(
            onClick = {
                navigateToCreatePinCode()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .testTag("pinCodes:create")
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Create PIN"
            )
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("pinCodes:loading"),
    )
}

@Composable
private fun EmptyState() {
    Text(
        text = stringResource(id = string.empty_header),
        modifier = Modifier
            .padding(16.dp)
            .testTag("pinCodes:emptyTitle")
    )
}

@Composable
private fun PinCodesContent(
    modifier: Modifier = Modifier,
    pinCodes: List<PinCode>,
    deletePinCode: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .testTag("pinCodes:list"),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        pinCodes.forEach { savedPinCode ->
            val pinCodeId = savedPinCode.id
            item(key = pinCodeId) {
                PinCodeItem(
                    name = savedPinCode.name,
                    code = savedPinCode.code,
                    deletePinCode = { deletePinCode(pinCodeId) }
                )
            }
        }

        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}
