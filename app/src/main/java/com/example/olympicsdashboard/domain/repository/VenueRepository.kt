package com.example.olympicsdashboard.domain.repository

import com.example.olympicsdashboard.domain.model.VenueModel

interface VenueRepository {

    suspend fun getVenues() : List<VenueModel>

}