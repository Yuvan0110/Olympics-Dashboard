package com.example.olympicsdashboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.ApiServiceImpl
import com.example.olympicsdashboard.data.repositoryImpl.CountryRepositoryImpl
import com.example.olympicsdashboard.data.repositoryImpl.EventRepositoryImpl
import com.example.olympicsdashboard.domain.model.CountryModel
import com.example.olympicsdashboard.domain.repository.CountryRepository
import com.example.olympicsdashboard.domain.repository.EventRepository
import com.example.olympicsdashboard.domain.usecase.CountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    private val countryUseCase: CountryUseCase
) : ViewModel() {

    private val _countriesByContinent = MutableStateFlow<Map<String, Int>>(emptyMap())
    val countriesByContinent: StateFlow<Map<String, Int>> get() = _countriesByContinent

    private val _countriesCount = MutableStateFlow(0)
    val countriesCount: StateFlow<Int> get() = _countriesCount

    private val _countriesRanking = MutableStateFlow<List<CountryModel>>(emptyList())
    val countriesRanking : StateFlow<List<CountryModel>> get() = _countriesRanking

    fun loadAllData() {
        viewModelScope.launch {
            _countriesByContinent.value = countryUseCase.getCountriesParticipatedByContinent()
            _countriesCount.value = countryUseCase.getCountriesCount()
            _countriesRanking.value = countryUseCase.getCountryMedals()
        }
    }
}


class CountryViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            val apiService: ApiService = ApiServiceImpl
            val eventRepo: EventRepository = EventRepositoryImpl(apiService)
            val countryRepo: CountryRepository = CountryRepositoryImpl(apiService)
            val useCase = CountryUseCase(countryRepo, eventRepo)
            return CountryViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


