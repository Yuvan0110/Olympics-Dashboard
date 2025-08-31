package com.example.olympicsdashboard.data.repositoryImpl

import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.toDomain
import com.example.olympicsdashboard.domain.model.SportModel
import com.example.olympicsdashboard.domain.repository.SportRepository

class SportRepositoryImpl(
    private val api: ApiService
) : SportRepository {
    override suspend fun getSports(): List<SportModel> {
        val response = api.getSports()
        return response.data.map { it.toDomain() }
    }

}