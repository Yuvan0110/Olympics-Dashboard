package com.example.olympicsdashboard.domain.usecase

import com.example.olympicsdashboard.domain.model.CountryModel
import com.example.olympicsdashboard.domain.repository.CountryRepository
import com.example.olympicsdashboard.domain.repository.EventRepository

class CountryUseCase(
    private val countryRepository: CountryRepository,
    private val eventRepository: EventRepository
) {
    suspend fun getCountriesParticipatedByContinent(): Map<String, Int> {
        val events = eventRepository.getEvents()
        val countries = countryRepository.getCountries()

        val countryByContinent = mutableMapOf<String, String>()
        for (country in countries) {
            countryByContinent[country.name] = country.continent
        }
        val participatedCountries = mutableSetOf<String>()
        for (event in events) {
            for (competitor in event.competitors) {
                participatedCountries.add(competitor.country)
            }
        }
        val result = mutableMapOf<String, Int>()
        for (country in participatedCountries) {
            val continent = countryByContinent[country] ?: "Nil"
            result[continent] = result.getOrDefault(continent, 0) + 1
        }

        return result
    }


    suspend fun getCountriesCount() : Int {
        val countries = countryRepository.getCountries()
        return countries.size
    }

    suspend fun getCountryMedals() : List<CountryModel> {
        val countries = countryRepository.getCountries()
        return countries.sortedByDescending { it.totalMedals }
    }
}