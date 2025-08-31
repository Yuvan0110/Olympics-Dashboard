package com.example.olympicsdashboard.presentation.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.olympicsdashboard.ui.theme.OlympicsDashboardTheme


@Composable
fun KpiCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
            .width(120.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KpiCardPreview(modifier: Modifier = Modifier) {
    OlympicsDashboardTheme {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KpiCard(
                    Modifier
                        .weight(1f),
                    "Events",
                    "48"
                )
                KpiCard(
                    Modifier.weight(1f),
                    "Countries",
                    "48"
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KpiCard(
                    Modifier
                        .weight(1f),
                    "Events",
                    "48"
                )
                KpiCard(
                    Modifier.weight(1f),
                    "Countries",
                    "48"
                )
            }
        }
    }
}
