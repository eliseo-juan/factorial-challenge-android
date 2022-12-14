package tech.eliseo.timetracker.ui.coponents

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.R
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    buttonState: MainButtonState = MainButtonState.Idle,
    onClick: () -> Unit = {}
) {

    val radius = when (buttonState) {
        MainButtonState.Idle -> 360.dp
        MainButtonState.Started -> 56.dp
    }
    val cornerRadius = animateDpAsState(targetValue = radius)

    Button(
        modifier = modifier
            .aspectRatio(1f),
        shape = RoundedCornerShape(cornerRadius.value),
        onClick = onClick
    ) {
        Text(
            text = when (buttonState) {
                MainButtonState.Idle -> stringResource(id = R.string.main_button_start)
                MainButtonState.Started -> stringResource(id = R.string.main_button_end)
            }.uppercase(),
            style = MaterialTheme.typography.displayLarge
        )
    }
}

sealed class MainButtonState {
    object Idle : MainButtonState()
    object Started : MainButtonState()
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    var buttonState = remember<MainButtonState> { MainButtonState.Idle }

    MyApplicationTheme {

        Box(Modifier.padding(16.dp)) {
            MainButton(buttonState = buttonState) {
                buttonState = when (buttonState) {
                    MainButtonState.Idle -> MainButtonState.Started
                    MainButtonState.Started -> MainButtonState.Idle
                }
            }
        }
    }
}