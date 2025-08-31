package com.example.olympicsdashboard.data.dto

import com.google.gson.annotations.SerializedName


data class SportsResponseDto (
    @SerializedName("data" ) var data : ArrayList<Sport> = arrayListOf()
)


data class Sport (
    @SerializedName("id"            ) var id           : String? = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("pictogram_url" ) var pictogramUrl : String? = null
)