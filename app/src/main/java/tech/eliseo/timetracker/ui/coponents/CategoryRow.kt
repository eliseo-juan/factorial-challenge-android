package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationDisabled
import androidx.compose.material.icons.rounded.LocationOff
import androidx.compose.material.icons.rounded.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.ui.formatter.CategoryFormatter
import tech.eliseo.timetracker.ui.preview.FakePreviewData
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    category: Category
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),

        ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryIconView(
                    modifier = Modifier.padding(16.dp),
                    category = category
                )
                Text(
                    modifier = Modifier,
                    text = CategoryFormatter.getName(category),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .alpha(0.5f)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                text = "Automatizaciones",
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(start = 12.dp, bottom = 16.dp, end = 12.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(32.dp),
                    label = { Text(text = "Localización") },
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.LocationOff,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    },
                    onClick = {}
                )
                AssistChip(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(32.dp),
                    label = { Text(text = "Red WiFi") },
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.WifiOff,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            FakePreviewData.getCategories().forEach {
                CategoryRow(modifier = Modifier.padding(8.dp), category = it)
            }
        }
    }
}