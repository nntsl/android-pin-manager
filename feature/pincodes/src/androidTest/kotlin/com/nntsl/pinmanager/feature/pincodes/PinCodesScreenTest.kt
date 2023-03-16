package com.nntsl.pinmanager.feature.pincodes

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class PinCodesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            PinCodesScreen(PinCodesUiState.Loading)
        }

        composeTestRule.onNodeWithTag("pinCodes:loading").assertExists()
    }

    @Test
    fun pinCodes_whenScreenIsShown_thenCreatePinFabIsShown() {
        composeTestRule.setContent {
            PinCodesScreen(PinCodesUiState.Success(listOf()))
        }

        composeTestRule.onNodeWithTag("pinCodes:create").assertExists().assertHasClickAction()
    }

    @Test
    fun pinCodes_whenLoadedEmptyList_thenEmptyStateIsShown() {
        composeTestRule.setContent {
            PinCodesScreen(PinCodesUiState.Success(listOf()))
        }

        composeTestRule.onNodeWithTag("pinCodes:emptyTitle").assertExists()
        composeTestRule.onNodeWithTag("pinCodes:create").assertExists().assertHasClickAction()
    }

    @Composable
    private fun PinCodesScreen(uiState: PinCodesUiState) {
        PinCodesScreen(
            uiState = uiState,
            deletePinCode = {},
            navigateToCreatePinCode = {}
        )
    }
}
