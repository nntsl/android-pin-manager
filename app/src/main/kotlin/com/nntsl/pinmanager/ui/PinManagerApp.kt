package com.nntsl.pinmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.nntsl.pinmanager.navigation.PinManagerNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinManagerApp(
    navController: NavHostController,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.imePadding()
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(padding)
        ) {
            PinManagerNavHost(
                navController = navController,
                onBackClick = onBackClick
            )
        }
    }
}
