package com.nntsl.pinmanager.feature.pincodes

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nntsl.pinmanager.feature.pincodes.R.string

@Composable
fun PinCodeItem(
    name: String,
    code: String,
    deletePinCode: () -> Unit
) {
    val (expanded, onChangeExpandedState) = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("pinCodes:item${name}")
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onChangeExpandedState(!expanded) },
            modifier = Modifier.testTag("pinCodes:item${name}:deleteMenu")
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(string.delete_pin_code)) },
                onClick = { deletePinCode() }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("pinCodes:item${name}:content")
                .padding(vertical = 4.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            onChangeExpandedState(!expanded)
                        }
                    )
                }
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            Text(
                text = stringResource(string.pin_code, code),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 4.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )
            )
            Divider()
        }
    }
}
