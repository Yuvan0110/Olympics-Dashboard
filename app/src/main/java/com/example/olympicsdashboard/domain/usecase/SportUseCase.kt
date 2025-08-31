package com.example.olympicsdashboard.domain.usecase

import com.example.olympicsdashboard.domain.model.SportModel
import com.example.olympicsdashboard.domain.repository.SportRepository

class SportUseCase(
    private val sportRepository: SportRepository
) {
    suspend fun getSportsCount() : Int {
        return sportRepository.getSports().size
    }
}