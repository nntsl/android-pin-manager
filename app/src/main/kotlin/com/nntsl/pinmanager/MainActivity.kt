package com.nntsl.pinmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nntsl.pinmanager.ui.PinManagerApp
import com.nntsl.pinmanager.ui.theme.PinManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            PinManagerTheme {
                PinManagerApp(
                    navController = navController,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
