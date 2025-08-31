package com.example.olympicsdashboard.domain.usecase

import com.example.olympicsdashboard.domain.model.VenueModel
import com.example.olympicsdashboard.domain.repository.VenueRepository

class VenueUseCase(
    private val venueRepository: VenueRepository
) {
    suspend fun getVenuesCount() : Int {
        return venueRepository.getVenues().size
    }
}