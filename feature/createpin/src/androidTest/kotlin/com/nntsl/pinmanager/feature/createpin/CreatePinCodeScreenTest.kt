package com.nntsl.pinmanager.feature.createpin

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class CreatePinCodeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            CreatePinCodeScreen(CreatePinCodeUiState.Loading)
        }

        composeTestRule.onNodeWithTag("createPinCode:pullRefreshIndicator").assertExists()
    }

    @Composable
    private fun CreatePinCodeScreen(uiState: CreatePinCodeUiState) {
        CreatePinCodeScreen(
            uiState = uiState,
            onBackClick = {},
            updatePinName = {},
            createPinCode = {}
        )
    }
}
