package com.nntsl.pinmanager.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nntsl.pinmanager.navigation.PinManagerNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinManagerApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.imePadding()
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            PinManagerNavHost(
                navController = navController
            )
        }
    }
}
