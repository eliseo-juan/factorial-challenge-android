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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    buttonState: MainButtonState = MainButtonState.Idle,
    onClick: () -> Unit = {}
) {

    val radius = when (buttonState) {
        MainButtonState.Idle -> 160.dp
        MainButtonState.Started -> 16.dp
    }
    val cornerRadius = animateDpAsState(targetValue = radius)

    Button(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(cornerRadius.value)),
        onClick = onClick) {
        Text(
            text = when (buttonState) {
                MainButtonState.Idle -> "Inicio"
                MainButtonState.Started -> "Fin"
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