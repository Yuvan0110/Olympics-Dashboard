package com.example.olympicsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.olympicsdashboard.presentation.design.CountriesBarChart
import com.example.olympicsdashboard.presentation.design.KpiCard
import com.example.olympicsdashboard.presentation.viewModel.CountryViewModel
import com.example.olympicsdashboard.presentation.viewModel.CountryViewModelFactory
import com.example.olympicsdashboard.presentation.viewModel.EventViewModel
import com.example.olympicsdashboard.presentation.viewModel.EventViewModelFactory
import com.example.olympicsdashboard.presentation.viewModel.SportViewModel
import com.example.olympicsdashboard.presentation.viewModel.SportViewModelFactory
import com.example.olympicsdashboard.presentation.viewModel.VenueViewModel
import com.example.olympicsdashboard.presentation.viewModel.VenueViewModelFactory
import com.example.olympicsdashboard.ui.theme.OlympicsDashboardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OlympicsDashboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.padding(innerPadding)
                    ){
                        KpiDataComp(
                            modifier = Modifier
                        )
                        CountriesBarChartComp(
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun KpiDataComp(
    modifier: Modifier = Modifier
) {
    val countryFactory = CountryViewModelFactory()
    val countryViewModel: CountryViewModel = viewModel(factory = countryFactory)

    val eventFactory = EventViewModelFactory()
    val eventViewModel: EventViewModel = viewModel(factory = eventFactory)

    val venueFactory = VenueViewModelFactory()
    val venueViewModel : VenueViewModel = viewModel(factory = venueFactory)

    val sportFactory = SportViewModelFactory()
    val sportViewModel : SportViewModel = viewModel(factory = sportFactory)

    val countriesCount by countryViewModel.countriesCount.collectAsState()
    val eventsCount by eventViewModel.eventsCount.collectAsState()
    val venuesCount by venueViewModel.venuesCount.collectAsState()
    val sportsCount by sportViewModel.sportsCount.collectAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
        ) {
            KpiCard(
                modifier, "Countries", countriesCount.toString()
            )
            KpiCard(
                modifier, "Sports", sportsCount.toString()
            )
        }

        Row() {
            KpiCard(
                modifier, "Events", eventsCount.toString()
            )
            KpiCard(
                modifier, "Venues", venuesCount.toString()
            )
        }
    }
}

@Composable
fun CountriesBarChartComp(modifier: Modifier = Modifier) {
    val factory = CountryViewModelFactory()
    val countryViewModel: CountryViewModel = viewModel(factory = factory)

    val countriesRanking by countryViewModel.countriesRanking.collectAsState()

    LaunchedEffect(Unit) {
        countryViewModel.loadAllData()
    }

    Box(
         modifier = modifier
    ){
        CountriesBarChart(
            modifier = modifier,
            countriesRanking
        )
    }
}

