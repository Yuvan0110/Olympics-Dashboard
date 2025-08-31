package com.example.olympicsdashboard.data.repositoryImpl

import com.example.olympicsdashboard.data.ApiService
import com.example.olympicsdashboard.data.toDomain
import com.example.olympicsdashboard.domain.model.EventModel
import com.example.olympicsdashboard.domain.repository.EventRepository

class EventRepositoryImpl(
    private val api: ApiService
) : EventRepository {

    override suspend fun getEvents(): List<EventModel> {
        val allEvents = mutableListOf<EventModel>()
        val firstResponse = api.getEvents(1)
        allEvents.addAll(firstResponse.data.map { it.toDomain() })
        val lastPage = firstResponse.meta?.lastPage ?: 1

        for (page in 2..lastPage) {
            val response = api.getEvents(page)
            allEvents.addAll(response.data.map { it.toDomain() })
        }
        return allEvents
    }

}

