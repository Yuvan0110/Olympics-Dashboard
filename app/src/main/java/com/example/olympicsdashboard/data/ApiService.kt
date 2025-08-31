package com.example.olympicsdashboard.data

import com.example.olympicsdashboard.data.dto.CountriesResponseDto
import com.example.olympicsdashboard.data.dto.EventsResponseDto
import com.example.olympicsdashboard.data.dto.SportsResponseDto
import com.example.olympicsdashboard.data.dto.VenuesResponseDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // countries endpoint
    @GET("countries")
    suspend fun getCountries() : CountriesResponseDto

    // sports endpoint
    @GET("disciplines")
    suspend fun getSports() : SportsResponseDto

    // events endpoint
    @GET("events")
    suspend fun getEvents(@Query("page") page : Int) : EventsResponseDto

    // venue endpoint
    @GET("venues")
    suspend fun getVenues() : VenuesResponseDto
}

object RetrofitInstance {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://apis.codante.io/olympic-games/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

object ApiServiceImpl : ApiService by RetrofitInstance.api
