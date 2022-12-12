@file:OptIn(ExperimentalMaterial3Api::class)

package tech.eliseo.timetracker.ui.screen.add_category

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import tech.eliseo.timetracker.R

@Composable
fun AddCategoryDialog(
    onRequestConfirmButton: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {

    val text = rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(id = R.string.category_dialog_add_title))
        },
        text = {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = {
                    Text(text = stringResource(id = R.string.category_dialog_add_label))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.category_dialog_add_hint))
                }
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onRequestConfirmButton(text.value)
                }
            ) {
                Text(stringResource(id = R.string.category_dialog_add_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(stringResource(id = R.string.category_dialog_add_cancel))
            }
        }
    )
}