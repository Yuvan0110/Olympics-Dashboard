package com.example.olympicsdashboard.data.repositoryImpl

import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.toDomain
import com.example.olympicsdashboard.domain.model.VenueModel
import com.example.olympicsdashboard.domain.repository.VenueRepository

class VenueRepositoryImpl(
    private val api: ApiService
) : VenueRepository {
    override suspend fun getVenues(): List<VenueModel> {
        val response = api.getVenues()
        return response.data.map { it.toDomain() }
    }
}