package com.example.olympicsdashboard.domain.repository

import com.example.olympicsdashboard.domain.model.SportModel

interface SportRepository {

    suspend fun getSports() : List<SportModel>

}