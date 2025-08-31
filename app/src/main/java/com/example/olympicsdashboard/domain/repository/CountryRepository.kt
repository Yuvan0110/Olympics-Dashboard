package com.example.olympicsdashboard.domain.repository


import com.example.olympicsdashboard.domain.model.CountryModel

interface CountryRepository {
    suspend fun getCountries() : List<CountryModel>

}