package com.example.olympicsdashboard.domain.repository

import com.example.olympicsdashboard.domain.model.EventModel

interface EventRepository {
    suspend fun getEvents() : List<EventModel>
}