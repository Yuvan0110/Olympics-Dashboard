package com.example.olympicsdashboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.ApiServiceImpl
import com.example.olympicsdashboard.data.repositoryImpl.SportRepositoryImpl
import com.example.olympicsdashboard.domain.usecase.SportUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportViewModel(
    private val sportUseCase: SportUseCase
) : ViewModel() {

    private val _sportsCount = MutableStateFlow<Int>(0)
    val sportsCount : StateFlow<Int> get() = _sportsCount

    fun loadAllData() {
        viewModelScope.launch {
            _sportsCount.value = sportUseCase.getSportsCount()
        }
    }
}

class SportViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SportViewModel::class.java)) {
            val apiService: ApiService = ApiServiceImpl
            val sportRepo = SportRepositoryImpl(apiService)
            val useCase = SportUseCase(sportRepo)
            return SportViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
