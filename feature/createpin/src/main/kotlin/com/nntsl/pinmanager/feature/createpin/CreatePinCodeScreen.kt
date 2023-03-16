package com.nntsl.pinmanager.feature.createpin

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nntsl.pinmanager.feature.createpin.R.string

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CreatePinCodeScreen(
    modifier: Modifier = Modifier,
    uiState: CreatePinCodeUiState,
    onBackClick: () -> Unit,
    updatePinName: (String) -> Unit,
    createPinCode: (String) -> Unit
) {
    val loading = uiState is CreatePinCodeUiState.Loading
    val pullRefreshState = rememberPullRefreshState(loading, {})

    when (uiState) {
        CreatePinCodeUiState.Saved -> {
            onBackClick()
        }
        is CreatePinCodeUiState.Data -> {
            CreatePinCodeContent(
                modifier = modifier,
                generatedPinCode = uiState.generatedPinCode,
                enableCreateButton = uiState.enableCreateButton,
                isErrorName = uiState.isErrorName,
                onBackClick = onBackClick,
                onValueChanged = updatePinName,
                createPinCode = createPinCode
            )
        }
        CreatePinCodeUiState.Loading -> {}
    }

    AnimatedVisibility(
        visible = uiState is CreatePinCodeUiState.Loading,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
        ) + fadeOut(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PullRefreshIndicator(
                refreshing = loading,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .pullRefreshIndicatorTransform(scale = true, state = pullRefreshState)
                    .testTag("createPinCode:pullRefreshIndicator"),
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun CreatePinCodeContent(
    modifier: Modifier = Modifier,
    generatedPinCode: String,
    isErrorName: Boolean,
    enableCreateButton: Boolean,
    onBackClick: () -> Unit,
    onValueChanged: (String) -> Unit,
    createPinCode: (String) -> Unit
) {
    var currentName by remember { mutableStateOf("") }
    val lazyListState: LazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        stickyHeader {
            CreatePinCodeToolbar(
                onBackClick = onBackClick
            )
        }

        item {
            OutlinedTextField(
                value = currentName,
                onValueChange = {
                    currentName = it
                    onValueChanged(currentName)
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                placeholder = {
                    Text(stringResource(string.pin_name_hint))
                }
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                if (isErrorName) {
                    Text(
                        text = stringResource(string.pin_name_already_exist),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Text(
                    text = stringResource(string.pin_code, generatedPinCode),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }
        }

        item {
            TextButton(
                enabled = enableCreateButton,
                onClick = {
                    createPinCode(currentName)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Text(
                    text = stringResource(string.create),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

@Composable
private fun CreatePinCodeToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { onBackClick() },
            modifier = Modifier.testTag("createPinCode:back")
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = string.back)
            )
        }
    }
}
