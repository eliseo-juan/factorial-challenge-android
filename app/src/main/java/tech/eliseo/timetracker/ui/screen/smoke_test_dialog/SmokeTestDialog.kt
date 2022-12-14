@file:OptIn(ExperimentalMaterial3Api::class)

package tech.eliseo.timetracker.ui.screen.smoke_test_dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SquareFoot
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import tech.eliseo.timetracker.R

@Composable
fun SmokeTestDialog(
    title: String,
    onConfirmRequest: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = stringResource(id = R.string.smoke_feature_message))
        },
        icon = {
            Icon(imageVector = Icons.Rounded.SquareFoot, contentDescription = null)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest()
                }
            ) {
                Text(stringResource(id = R.string.smoke_feature_positive_button))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(stringResource(id = R.string.smoke_feature_negative_button))
            }
        }
    )
}