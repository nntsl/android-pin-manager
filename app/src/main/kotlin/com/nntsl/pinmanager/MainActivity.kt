package com.nntsl.pinmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nntsl.pinmanager.ui.PinManagerApp
import com.nntsl.pinmanager.ui.theme.PinManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PinManagerTheme {
                PinManagerApp()
            }
        }
    }
}
