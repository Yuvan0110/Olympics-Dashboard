package com.example.olympicsdashboard.presentation.design

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.olympicsdashboard.domain.model.CountryModel
import com.example.olympicsdashboard.presentation.viewModel.CountryViewModel
import com.example.olympicsdashboard.ui.theme.OlympicsDashboardTheme
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun CountriesBarChart(
    modifier: Modifier = Modifier,
    countries: List<CountryModel>
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp), // flexible height
        factory = { context -> BarChart(context) },
        update = { chart ->

            if (countries.isNotEmpty()) {
                val goldEntries = ArrayList<BarEntry>()
                val silverEntries = ArrayList<BarEntry>()
                val bronzeEntries = ArrayList<BarEntry>()

                countries.forEachIndexed { index, country ->
                    goldEntries.add(BarEntry(index.toFloat(), country.goldMedals.toFloat()))
                    silverEntries.add(BarEntry(index.toFloat(), country.silverMedals.toFloat()))
                    bronzeEntries.add(BarEntry(index.toFloat(), country.bronzeMedals.toFloat()))
                }

                val goldSet = BarDataSet(goldEntries, "Gold").apply { color = Color.rgb(255, 215, 0) }
                val silverSet = BarDataSet(silverEntries, "Silver").apply { color = Color.GRAY }
                val bronzeSet = BarDataSet(bronzeEntries, "Bronze").apply { color = Color.rgb(205, 127, 50) }

                val barData = BarData(goldSet, silverSet, bronzeSet)
                val groupSpace = 0.2f
                val barSpace = 0.05f
                val barWidth = 0.25f

                barData.barWidth = barWidth
                chart.data = barData

                val labels = countries.map { it.id }

                chart.xAxis.apply {
                    valueFormatter = IndexAxisValueFormatter(labels)
                    position = XAxis.XAxisPosition.BOTTOM
                    granularity = 1f
                    setDrawGridLines(false)
                    labelRotationAngle = -30f
                    axisMinimum = 0f
                    axisMaximum = countries.size.toFloat()
                }

                chart.axisLeft.axisMinimum = 0f
                chart.axisRight.isEnabled = false
                chart.description.isEnabled = false
                chart.legend.apply {
                    isEnabled = true
                    verticalAlignment = Legend.LegendVerticalAlignment.TOP
                    horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                }

                chart.groupBars(0f, groupSpace, barSpace)
                chart.setFitBars(true)
                chart.setVisibleXRangeMaximum(6f)
                chart.isDragEnabled = true
                chart.setScaleEnabled(true)
                chart.setPinchZoom(true)
                chart.moveViewToX(0f)

                chart.invalidate()
            }
        }
    )
}

