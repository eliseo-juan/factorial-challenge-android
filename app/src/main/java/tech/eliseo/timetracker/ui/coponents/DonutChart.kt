package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    slices: List<Pair<Color, Float>> = emptyList(),
    thickness: Dp = 24.dp
) {
    val sumOfValues: Float = slices.map { it.second }.sum()

    val proportions = slices.map {
        it.second * 100 / sumOfValues
    }

    val sweepAngles = proportions.map {
        360 * it / 100
    }

    Canvas(
        modifier = modifier
            .padding(thickness/2)
    ) {
        var startAngle = -90f

        for (i in sweepAngles.indices) {
            drawArc(
                color = slices[i].first,
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = false,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
            )
            startAngle += sweepAngles[i]
        }
    }
}

@Preview
@Composable
private fun DonutChartPreview() {
    Box(modifier = Modifier.size(200.dp)) {
        DonutChart(
            modifier = Modifier.fillMaxSize(),
            slices = listOf(
                Pair(Color.Red, 2f),
                Pair(Color.Blue, 3f),
                Pair(Color.Yellow, 5f)
            )
        )
    }
}