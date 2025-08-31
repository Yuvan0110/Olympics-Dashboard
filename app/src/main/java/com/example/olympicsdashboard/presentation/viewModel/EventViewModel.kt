package com.example.olympicsdashboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.ApiServiceImpl
import com.example.olympicsdashboard.data.repositoryImpl.EventRepositoryImpl
import com.example.olympicsdashboard.domain.usecase.EventUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel(
    private val eventsUseCase: EventUseCase
) : ViewModel(){


    private val _sportsByGender = MutableStateFlow<Map<String, Int>>(emptyMap())
    val eventsByGender : StateFlow<Map<String, Int>> get() = _sportsByGender

    private val _eventsCount = MutableStateFlow<Int>(0)
    val eventsCount : StateFlow<Int> get() = _eventsCount

    private val _countryParticipationBySport = MutableStateFlow<Map<String, Int>>(emptyMap())
    val countryParticipationBySport : StateFlow<Map<String, Int>> = _countryParticipationBySport

    private val _countryParticipationCount = MutableStateFlow<Map<String, Int>>(emptyMap())
    val countryParticipationCount : StateFlow<Map<String, Int>> = _countryParticipationCount

    fun loadAllData() {
        viewModelScope.launch {
            _sportsByGender.value = eventsUseCase.getSportsGroupedByGender()
            _eventsCount.value = eventsUseCase.getEventsCount()
            _countryParticipationBySport.value = eventsUseCase.getCountryParticipationBySport()
            _countryParticipationCount.value = eventsUseCase.getCountryParticipationCount()
        }
    }

}


class EventViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            val apiService: ApiService = ApiServiceImpl
            val eventRepo = EventRepositoryImpl(apiService)
            val useCase = EventUseCase(eventRepo)
            return EventViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}