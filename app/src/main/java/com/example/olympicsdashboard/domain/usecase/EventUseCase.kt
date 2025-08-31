package com.example.olympicsdashboard.domain.usecase

import com.example.olympicsdashboard.domain.repository.EventRepository

class EventUseCase(
    private val eventRepository: EventRepository
) {

    suspend fun getSportsGroupedByGender(): Map<String, Int> {
        val events = eventRepository.getEvents()
        val result = mutableMapOf<String, Int>()
        val eventsByGender = events.groupBy { it.gender }

        for ((gender, genderEvents) in eventsByGender) {
            val sports = mutableSetOf<String>()
            for (event in genderEvents) {
                sports.add(event.sport)
            }
            result[gender] = sports.size
        }
        return result
    }


    suspend fun getEventsCount() : Int {
        val events = eventRepository.getEvents()
        return events.size
    }

    suspend fun getCountryParticipationBySport(): Map<String, Int> {
        val events = eventRepository.getEvents()
        val result = mutableMapOf<String, Int>()
        val eventsBySport = events.groupBy { it.sport }

        for ((sport, sportEvents) in eventsBySport) {
            val countries = mutableSetOf<String>()
            for (event in sportEvents) {
                for (competitor in event.competitors) {
                    countries.add(competitor.country)
                }
            }
            result[sport] = countries.size
        }
        return result
    }

    suspend fun getCountryParticipationCount(): Map<String, Int> {
        val events = eventRepository.getEvents()
        val participationCount = mutableMapOf<String, Int>()

        events.forEach { event ->
            val uniqueCountries = event.competitors.map { it.country }.toSet()
            uniqueCountries.forEach { country ->
                participationCount[country] = participationCount.getOrDefault(country, 0) + 1
            }
        }
        return participationCount
    }

}