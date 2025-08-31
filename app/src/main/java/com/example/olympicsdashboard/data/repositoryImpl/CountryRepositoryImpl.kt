package com.example.olympicsdashboard.data.repositoryImpl

import android.util.Log
import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.ApiServiceImpl
import com.example.olympicsdashboard.data.toDomain
import com.example.olympicsdashboard.domain.model.CountryModel
import com.example.olympicsdashboard.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val api: ApiService
) : CountryRepository {
    override suspend fun getCountries(): List<CountryModel> {
        val response =  api.getCountries()
        Log.d("API", "Countries response: ${response.data}")
        val mapped = response.data.map { it.toDomain() }
        Log.d("Repo", "Mapped countries: $mapped")
        return mapped
    }
}
