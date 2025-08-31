package com.example.olympicsdashboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.ApiServiceImpl
import com.example.olympicsdashboard.data.repositoryImpl.VenueRepositoryImpl
import com.example.olympicsdashboard.domain.usecase.VenueUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VenueViewModel(
    private val venueUseCase: VenueUseCase
) : ViewModel(){

    private val _venuesCount = MutableStateFlow<Int>(0)
    val venuesCount : StateFlow<Int> = _venuesCount

    fun loadAllData() {
        viewModelScope.launch {
            _venuesCount.value = venueUseCase.getVenuesCount()
        }
    }
}


class VenueViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VenueViewModel::class.java)) {
            val apiService: ApiService = ApiServiceImpl
            val venueRepo = VenueRepositoryImpl(apiService)
            val useCase = VenueUseCase(venueRepo)
            return VenueViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
