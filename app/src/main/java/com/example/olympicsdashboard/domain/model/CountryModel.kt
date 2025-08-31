package com.example.olympicsdashboard.domain.model
data class CountryModel (
    val id: String,
    val name: String,
    val continent: String,
    val flagUrl: String,
    val goldMedals: Int,
    val silverMedals: Int,
    val bronzeMedals: Int,
    val totalMedals: Int,
    val rank: Int
)

