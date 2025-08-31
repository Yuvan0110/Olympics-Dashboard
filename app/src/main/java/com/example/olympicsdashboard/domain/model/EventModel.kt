package com.example.olympicsdashboard.domain.model

data class EventModel(
    val id: Int,
    val sport: String,
    val venue: String,
    val gender: String,
    val startDate: String,
    val endDate: String,
    val status: String,
    val competitors: List<CompetitorModel>
)

data class CompetitorModel(
    val country: String,
    val flagUrl: String,
    val name: String,
    val score: String,
    val result: String
)
